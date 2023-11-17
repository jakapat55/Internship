<script setup>
import { onBeforeUpdate, onMounted, ref } from 'vue';
import { useRouter, RouterLink } from 'vue-router'
import { userStore } from '../stores/users.js'

const yourColor = ref()
const back = "/imgs/backBtn.svg"
const creditList = "./imgs/credit-list.svg";
const myLogo = "./imgs/logo.svg";
const tutoPop = ref([{ name: 'tuto1', path: "./imgs/tuto1.svg" }, { name: 'tuto2', path: "./imgs/tuto2.svg" }, { name: 'tuto3', path: "./imgs/tuto3.svg" }, { name: 'tuto4', path: "./imgs/tuto4.svg" }, { name: 'tuto5', path: "./imgs/tuto5.svg" }, { name: 'tuto6', path: "./imgs/tuto6.svg" }])
const isClickTuto = ref(false)
const creditBtn = ref(false);
const currentPop = ref(0)
const users = userStore()
const router = useRouter()
const nextBtn = ref(false)
const preBtn = ref(false)

onMounted(async () => {
    await users.fetchAllUser()
    yourColor.value = users.user[users.user.length - 1]?.color
})

onBeforeUpdate(() => {
    if (currentPop.value === 5) {
        nextBtn.value = true
    }
    if (currentPop.value < 5) {
        nextBtn.value = false
    }
    if (currentPop.value === 0) {
        preBtn.value = true
    }
    if (currentPop.value > 0) {
        preBtn.value = false
    }
})

const tutoBtn = () => {
    isClickTuto.value = true
};
const nextTuto = () => {
    currentPop.value++
}
const preTuto = () => {
    currentPop.value--
}
const creditPop = () => {
    return (creditBtn.value = true);
};

</script>
 
<template>
    <div>
        <div class="absolute left-8 top-8 border-b-2 flex flex-row rounded-lg"
            style="height: 80px; background-color: #0f0f0f7b;">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
                class="w-16 h-16 mt-2 iconcolor">
                <path stroke-linecap="round" stroke-linejoin="round"
                    d="M15.75 6a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0zM4.501 20.118a7.5 7.5 0 0114.998 0A17.933 17.933 0 0112 21.75c-2.676 0-5.216-.584-7.499-1.632z" />
            </svg>
            <div v-for="(data, index) in users.user">
                <div v-if="index === users.user.length - 1">
                    <p class="text-3xl mt-5 ml-2 text-white">Hi, {{ data.name }}</p>
                </div>
            </div>
            <button>
                <RouterLink :to="{ name: 'UpdateUser' }">
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                        stroke="currentColor" class="w-6 h-6 ml-3 stroke-white">
                        <path stroke-linecap="round" stroke-linejoin="round"
                            d="M16.862 4.487l1.687-1.688a1.875 1.875 0 112.652 2.652L10.582 16.07a4.5 4.5 0 01-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 011.13-1.897l8.932-8.931zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0115.75 21H5.25A2.25 2.25 0 013 18.75V8.25A2.25 2.25 0 015.25 6H10" />
                    </svg>
                </RouterLink>
            </button>
        </div>
        <div>
            <div class="bg-bg-firstPage bg-cover w-full flex flex-col items-center" style="height: 750px">
                <img :src="myLogo" style="width: 520px; height: 423px" />
                <div class="flex flex-col space-y-3">
                    <RouterLink :to="{ name: 'category' }">
                        <button
                            class="cursor-pointer rounded-full bg-yellow-300 font-semibold transition duration-200 ease-in-out hover:bg-yellow-200 text-indigo-950 text-5xl"
                            style="width: 311px; height: 64px">
                            START
                        </button>
                    </RouterLink>
                    <div>
                        <button
                            class="cursor-pointer rounded-full bg-yellow-300 font-semibold transition duration-200 ease-in-out hover:bg-yellow-200 text-indigo-950 text-5xl"
                            style="width: 311px; height: 64px">
                            <RouterLink :to="{ name: 'ranking' }">RANKING</RouterLink>
                        </button>
                    </div>
                    <div>
                        <button
                            class="cursor-pointer rounded-full bg-yellow-300 font-semibold transition duration-200 ease-in-out hover:bg-yellow-200 text-indigo-950 text-5xl"
                            style="width: 311px; height: 64px" @click="tutoBtn()">
                            TUTORIAL
                        </button>
                        <div v-show="isClickTuto">
                            <div class="popup overflow-hidden">
                                <div
                                    class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-screen h-screen bg-black opacity-60">
                                </div>
                            </div>
                            <div class="popup relative">
                                <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 bg-yellow-200 rounded-xl"
                                    style="width: 1051px; height: 679px">
                                    <div class="top-10">
                                        <button style="margin-left: 1005px"
                                            class="absolute font-bold text-gray-800 mt-2 p-2 text-2xl rounded-md hover:bg-red-400 transition duration-500 ease-in-out flex-col"
                                            @click="isClickTuto = false">
                                            X
                                        </button>
                                        <div class="flex justify-center relative">
                                            <div class="bg-[#FED501] rounded-[50px] p-3 mt-3 left-[40%] absolute">
                                                <h3 class="text-[#253057] font-semibold text-5xl">TUTORIAL</h3>
                                            </div>
                                            <div class="flex justify-center items-center absolute" :key="currentPop"
                                                v-if="currentPop < tutoPop.length">
                                                <img :src="tutoPop[currentPop].path"
                                                    style="padding-top: 100px; width: 950px;" />
                                            </div>
                                            <div class="space-x-24 flex justify-center mr-20">
                                                <button @click="preTuto" :disabled="preBtn">
                                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                                        stroke-width="1.5" stroke="black" class="w-20 h-20 absolute"
                                                        style="margin-top: 590px;">
                                                        <path stroke-linecap="round" stroke-linejoin="round"
                                                            d="M11.25 9l-3 3m0 0l3 3m-3-3h7.5M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                                                    </svg>
                                                </button>
                                                <button @click="nextTuto" :disabled="nextBtn">
                                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                                        stroke-width="1.5" stroke="black" class="w-20 h-20 absolute"
                                                        style="margin-top: 590px;">
                                                        <path stroke-linecap="round" stroke-linejoin="round"
                                                            d="M12.75 15l3-3m0 0l-3-3m3 3h-7.5M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                                                    </svg>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <button value="Go back to previous page">
                            <RouterLink :to="{ name: 'CreateUser' }">
                                <img :src="back" style="position: absolute;" class="bottom-1 left-1 floating-img">
                            </RouterLink>
                        </button>
                        <button
                            class="cursor-pointer rounded-full bg-yellow-300 font-semibold transition duration-200 ease-in-out hover:bg-yellow-200 text-indigo-950 text-5xl"
                            style="width: 311px; height: 64px" @click="creditPop()">
                            CREDITS
                        </button>
                        <div v-show="creditBtn">
                            <div class="popup overflow-hidden">
                                <div
                                    class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-screen h-screen bg-black opacity-60">
                                </div>
                            </div>
                            <div class="popup">
                                <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 bg-yellow-200 rounded-xl"
                                    style="width: 994px; height: 623px">
                                    <div class="top-10">
                                        <button style="margin-left: 950px"
                                            class="font-bold text-gray-800 mt-2 p-2 text-2xl rounded-md hover:bg-red-400 transition duration-500 ease-in-out flex-col"
                                            @click="creditBtn = false">
                                            X
                                        </button>
                                        <div>
                                            <img :src="creditList" style="padding-right: 80px;" />
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <p class="font-semibold text-yellow-300 text-xl" style="padding-left: 1350px">
                    By QUARTZ
                </p>
            </div>
        </div>
    </div>
</template>

<style scoped>
.iconcolor {
    fill: v-bind(yourColor);
    stroke: v-bind(yourColor);
}

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

.floating-img {
    animation: floatAnimation 3s ease-in-out infinite;
}

</style>