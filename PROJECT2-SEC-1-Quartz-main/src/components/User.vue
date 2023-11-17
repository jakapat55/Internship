<script setup>
import createUser from '../composables/createUser';
import { ref } from 'vue';
const rawData = ref({
    name: "",
    score: 0
})

const submitUser = async () => {
    try {
        const objData = {
            name: rawData.value.name,
            score: rawData.value.score
        }
        const response = await createUser(objData);
    } catch (err) {
        console.error(err);
    }
}

const myLogo = "./imgs/logo.svg";
</script>
 
<template>
<div>
    <div class="bg-user bg-cover w-full flex flex-col items-center" style="height: 760px">
        <img :src="myLogo" style="width: 620px; height: 500px" />
        <input type="text" 
        class="w-1/4 p-7 border rounded-xl mr-5 font-bold text-3xl text-center" 
        v-model="rawData.name"
        style="height: 80px; opacity: 800%;"  
        @keydown.space.prevent
        />
        <RouterLink :to="{ name: 'category' }">
            <button
            class="cursor-pointer rounded-full bg-[#312F26] font-semibold transition duration-200 ease-in-out hover:bg-yellow-300 hover:text-black text-[#FFE890] text-5xl"
            style="width: 200px; height: 78px; margin-top: 40px;"
            @click="submitUser"
            >
            PLAY
            </button>
        </RouterLink>
    </div>
</div>
</template>
 
<style scoped>

</style>