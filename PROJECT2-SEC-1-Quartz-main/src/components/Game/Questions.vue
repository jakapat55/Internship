<script setup>
import { ref, onMounted, computed, onBeforeUpdate } from "vue";
import { useRouter } from "vue-router"
import { RouterLink } from "vue-router"
import { userStore } from "./../../stores/users";
import { fetchFlower, fetchFruit, fetchVegie } from "./../../scripts/getMethod.js"
//images, icons, etc.
const pronounceBg = "/imgs/bg-pronounce.svg";
const hint = "/imgs/hint.svg";
const home = "/imgs/home-btn.svg"

//variables
const users = userStore()
const id = ref()
const ansInput = ref([]);
const correctPop = ref(false);
const incorrectPop = ref(false);
const score = ref(0);
const remainingTime = ref(0);
const isSubmitted = ref([]);
const timeoutPop = ref(false);
const endPop = ref(false);
const quests = ref([])
const showHint = ref(new Array(quests.length).fill(false));
const router = useRouter()
const currentPage = ref(0)
const skipBtn = ref(false)
const username = ref('')
const existingScore = ref()
const yourColor = ref()

//fn
const upDateScore = async()=> {
    const objData = {
        name: username.value,
        color: yourColor.value,
        score: score.value
    }
    
    await users.updateUserData(id.value, objData)
    router.push({ name: 'ranking' })

}
const selectedCategory = ref(router.currentRoute.value.params);
//hint fn
const hintBtn = (index) => {
    showHint.value[index] = !showHint.value[index];
    if (selectedCategory.value.mode === "easy") {
        score.value -= 0.5;
    } else if (selectedCategory.value.mode === "normal") {
        score.value -= 1;
    } else if (selectedCategory.value.mode === "hard") {
        score.value -= 1.5;
    }
    setTimeout(() => {
        showHint.value[index] = !showHint.value[index];
    }, 5000);
};
//check ans
const correctAns = ref(10)
const falseAns = ref(0)
const checkAnswer = (index) => {
    if (!isSubmitted[index]) {
        isSubmitted[index] = true;
        let answer = ansInput.value;
        if (answer === quests.value[index].ans) {
            correctPop.value = true;
            
            setTimeout(() => {
                correctPop.value = false;
                currentPage.value++
                
                if (selectedCategory.value.mode === "easy") {
                    score.value += 1;
                } else if (selectedCategory.value.mode === "normal") {
                    score.value += 2;
                } else if (selectedCategory.value.mode === "hard") {
                    score.value += 3;
                }
                ansInput.value = ''
            }, 1000);
            return true;
        } else {
            incorrectPop.value = true;
            correctAns.value -= 1
            falseAns.value++
            setTimeout(() => {
                incorrectPop.value = false;
                currentPage.value++;
                ansInput.value = ''
            }, 1000);
            return false;
        }
    }
};
//time out fn
const startTimeout = () => {
    // remainingTime.value = 300000;
    if (selectedCategory.value.mode === "easy") {
        remainingTime.value = 300000;
    } else if (selectedCategory.value.mode === "normal") {
        remainingTime.value = 240000;
    } else if (selectedCategory.value.mode === "hard") {
        remainingTime.value = 180000;
    }
    const countdownInterval = setInterval(() => {
        remainingTime.value -= 100;
        if (remainingTime.value <= 0) {
            clearInterval(countdownInterval);
            timeoutPop.value = true;
        }
    }, 100);
};
const remainingMinutes = computed(() => {
    const minutes = Math.floor(remainingTime.value / 60000);
    return minutes < 10 ? `0${minutes}` : `${minutes}`;
});
const remainingSeconds = computed(() => {
    const seconds = Math.floor((remainingTime.value % 60000) / 1000);
    return seconds < 10 ? `0${seconds}` : `${seconds}`;
});

const scoreText = computed(() => {
  if (selectedCategory.value.mode === "easy") {
    return `${score.value} / 10`;
  } else if (selectedCategory.value.mode === "normal") {
    return `${score.value} / 20`;
  } else if (selectedCategory.value.mode === "hard") {
    return `${score.value} / 30`;
  }
});
onMounted(async () => {
    await users.fetchAllUser()
    id.value = parseInt(users.user[users.user.length - 1].id)
    username.value = users.user[users.user.length - 1].name
    existingScore.value = parseInt(users.user[users.user.length - 1].score)
    yourColor.value = users.user[users.user.length - 1].color
    startTimeout()
    if (selectedCategory.value.category == 'fruit') {
        const fruitsData = await fetchFruit()
        fruitsData.map((fruit) => {
            if (selectedCategory.value.mode == "easy") {
                quests.value = fruit.mode[0].questions
                return quests.value
            }
            else if (selectedCategory.value.mode == "normal") {
                quests.value = fruit.mode[1].questions
                return quests.value
            }
            else if (selectedCategory.value.mode == "hard") {
                quests.value = fruit.mode[2].questions
                return quests.value
            }
            return fruit
        })
    }
    else if (selectedCategory.value.category == 'flower') {
        const flowersData = await fetchFlower()
        flowersData.map((flower) => {
            if (selectedCategory.value.mode == "easy") {
                quests.value = flower.mode[0].questions
                return quests.value
            }
            else if (selectedCategory.value.mode == "normal") {
                quests.value = flower.mode[1].questions
                return quests.value
            }
            else if (selectedCategory.value.mode == "hard") {
                quests.value = flower.mode[2].questions
                return quests.value
            }
            return flower
        })
    }
    else if (selectedCategory.value.category == 'vegie') {
        const vegieData = await fetchVegie()
        vegieData.map((vegie) => {
            if (selectedCategory.value.mode == "easy") {
                quests.value = vegie.mode[0].questions
                return quests.value
            }
            else if (selectedCategory.value.mode == "normal") {
                quests.value = vegie.mode[1].questions
                return quests.value
            }
            else if (selectedCategory.value.mode == "hard") {
                quests.value = vegie.mode[2].questions
                return quests.value
            }
            return vegie
        })
    }
})
const currentCategory = computed(() => {
    if (selectedCategory.value.category === 'fruit') {
        return 'fruit'
    }
    if (selectedCategory.value.category === 'vegie') {
        return 'vegie'
    }
    if (selectedCategory.value.category === 'flower') {
        return 'flower'
    }
})
const bgDynamic = ref(currentCategory.value)
const skip = () => {
    currentPage.value++;
    correctAns.value--
    falseAns.value++
    ansInput.value = ""
};
onBeforeUpdate(() => {
    if (currentPage.value === 9) {
        skipBtn.value = true
    }
    if (currentPage.value > 9) {
        endPop.value = true
    }
    if(endPop.value === true){
        timeoutPop.value = false;
    }
})
</script>
<template>
    <div class="bg-cover w-full border border-b-8 border-orange-400" style="height: 770px"
     :class="bgDynamic === 'fruit' ? 'bgFruit' : (bgDynamic === 'flower' ? 'bgFlower' : 'bgVegie')" :key="currentPage"
        v-if="currentPage < quests.length">
        <div>
            <div class="w-full relative" id="header">
                <p class="absolute top-6 end-10 font-extrabold text-6xl rounded-[100px] p-[15px]" 
                            style="background-color:#312807 ; opacity:75%;">
                            {{ scoreText }}
                        </p>
                <div class="flex justify-center">
                    <p v-if="remainingTime > 0" class="font-extrabold  absolute w-[200px] text-center rounded-[50px] mt-[40px]"
                        style="font-size:50px;"
                        :class="remainingMinutes <= 0 && remainingSeconds <= 10 ? 'text-red-600 bg-black time-caution bg-opacity-40' : 'text-white bg-amber-900 bg-opacity-70 '">
                        {{ remainingMinutes }} : {{ remainingSeconds }}
                    </p> 
                    <img :src="pronounceBg" class="absolute top-[130px] h-14" />
                    <p class="absolute top-[130px] pt-1 font-extrabold text-3xl">
                        {{ quests[currentPage].pronounce }}
                    </p>
                    <div v-for="(img, index) in quests[currentPage].pic" :key="index" class="pt-[200px]">
                        <div class="drop-shadow-2xl hover:drop-shadow-md" style="height: 290px; margin-top: 20px;">
                            <img :src="img.path"
                                class="ml-3 mr-3 rounded-lg ring-4 ring-white ring-offset-4 ring-offset-slate-50 dark:ring-offset-amber-900"
                                style="width: 250px; height: 250px;" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="w-full flex flex-row justify-center items-center pt-[15px]" id="middle">
                <input type="text" class="w-1/4 p-7 border rounded-xl mr-5 font-bold text-3xl text-center"
                    style="height: 80px;" v-model="ansInput" @keydown.space.prevent />
                    <div class="text-white text-xl">
                    </div>
                <button @click="checkAnswer(currentPage)" type="submit"
                    class="btn bg-amber-950 rounded-full w-32 h-12 font-bold text-lg text-yellow-100"
                    :disabled="isSubmitted[currentPage] || !ansInput">
                    ANSWER
                </button>   
                <button @click="skip" type="submit"
                    class="ml-2 btn bg-amber-950 rounded-full w-32 h-12 font-bold text-lg text-yellow-100"
                    :disabled="skipBtn">
                    SKIP
                </button>
            </div>
            <div id="footer" class="w-full relative ">
                <button v-on:click="hintBtn(currentPage)">
                            <img :src="hint" class="absolute end-32 pr-1 floating-img" style="margin-top: 10px;" />
                        </button>
                        <button value="Go back to home page">
                            <RouterLink :to="{ name: 'Homepage' }"><img :src="home" class="absolute end-7 floating-img" style="margin-top: 10px;" />
                            </RouterLink>
                        </button>
                        <div class="floating-img flex justify-end mr-36" v-show="showHint[currentPage]">
                            <p
                                class="absolute text-center end-24 text-xl font-bold text-black border-8  bg-orange-200 border-orange-400 rounded-3xl w-[280px] p-4"
                                style="padding-left: 30px; padding-right: 30px;">
                                {{ quests[currentPage].hint }}
                            </p>
                        </div>
            </div>
        </div>
        <div v-show="correctPop">
            <div class="popup overflow-hidden">
                <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-screen h-screen bg-black opacity-60">
                </div>
            </div>
            <div class="popup">
                <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-1/4 h-36 bg-green-500 rounded-xl">
                    <div class="top-10">
                        <p class="text-black text-center text-4xl mt-12">CORRECT!!</p>
                    </div>
                </div>
            </div>
        </div>
        <div v-show="incorrectPop">
            <div class="popup">
                <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-screen h-screen bg-black opacity-60">
                </div>
            </div>
            <div class="popup">
                <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-1/4 h-36 bg-red-500 rounded-xl">
                    <div class="top-10">
                        <p class="text-black text-center text-4xl mt-12">INCORRECT!!</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div v-show="endPop">
        <div class="popup overflow-hidden">
            <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-screen h-screen bg-black opacity-60">
            </div>
        </div>
        <div class="popup">
            <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-1/3 h-60 bg-yellow-200 rounded-xl">
                <div class="">
                    <button style="margin-left: 450px"
                        class="font-bold text-gray-800 pl-3 pr-3 mt-2 text-2xl rounded-md hover:bg-red-400 transition duration-500 ease-in-out flex-col"
                       @click="upDateScore">
                    X
                    </button>
                    <div class="flex flex-col space-y-3 justify-center items-center">
                        <p class="text-center text-4xl text-green-700">
                            Correct: {{ correctAns }}
                        </p>
                        <p class="text-center text-4xl text-red-700">
                            False: {{ falseAns }}
                        </p>
                        <p class="text-center text-4xl text-indigo-950 bg-amber-500 pt-1 h-14 w-96 rounded-full">
                            Total Score: {{ scoreText }}
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div v-show="timeoutPop">
            <div class="popup">
                <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-screen h-screen bg-black opacity-60">
                </div>
            </div>
            <div class="popup">
                <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-1/4 h-36 bg-gray-400 rounded-xl">
                    <button style="margin-left: 320px"
                        class="font-bold text-gray-800 pl-3 pr-3 mt-2 text-2xl rounded-md hover:bg-red-400 transition duration-500 ease-in-out flex-col"
                       ><RouterLink :to="{name: 'Homepage'}">
                    X
                </RouterLink>
                    </button>
                    <div class="top-10">
                        <p class="text-black text-center text-4xl mt-3">Time Out!!</p>
                    </div>
                </div>
            </div>
        </div>
</template>
 
<style scoped>
p {
    font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
        Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}

@keyframes floatAnimation {
    0% {
        transform: translateY(0px);
    }

    50% {
        transform: translateY(-10px);
    }

    100% {
        transform: translateY(0px);
    }
}
@keyframes floatAnimationAxiosX {
    0% {
        transform: translateX(0px);
    }

    50% {
        transform: translateX(-10px);
    }

    100% {
        transform: translateX(0px);
    }
}
.floating-img {
    animation: floatAnimation 3s ease-in-out infinite;
}

.time-caution {
    animation: floatAnimationAxiosX 0.5s infinite;
    font-size: 110px;
    width: 15%;
    height: 70px;

}
.correct-ans {
    border: 5px solid limegreen;
}
.incorrect-ans {
    border: 5px solid red;
}
.bgFruit {
    background-image: url('/imgs/bg-fruit-mode.svg')
}
.bgVegie {
    background-image: url('/imgs/bg-vegie-mode.svg');
}
.bgFlower {
    background-image: url('/imgs/bg-flower-mode.svg');
}
</style>
