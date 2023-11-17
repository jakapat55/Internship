<script setup>
import { onBeforeUpdate, onMounted, ref } from 'vue';
import { userStore } from './../../stores/users.js'

//imgs
const bg = './imgs/bg-user.png'
const logo = './imgs/logo.svg'

const username = ref()
const users = userStore()
const useBtn = ref(false)
const duplicatePop = ref(false)

onMounted(async () => {
    await users.fetchAllUser()
})

onBeforeUpdate(() => {
    if (username.value !== undefined || username.value !== '') {
        const isDuplicate = users.user.some(u => username.value === u.name)
        if(isDuplicate === true){
            useBtn.value = true
            duplicatePop.value = true
        }
        if(isDuplicate === false){
            useBtn.value = false
            duplicatePop.value = false
        }
    }
})

const createUser = async () => {
    try {
        const res = await fetch('http://localhost:5002/users', {
            method: 'POST',
            headers: {
                "content-type": "application/json"
            },
            body: JSON.stringify({
                name: username.value,
                score: 0
            })
        })
        if (res.ok) {
            const addedUser = await res.json()
            users.user.push(addedUser)
        }
        else {
            const errorRes = await res.json();
            alert(`Error: ${errorRes.message || 'Unknown error'}`);
        }
    } catch (err) {
        console.error(err);
        alert('An unexpected error occurred');
    }
}

</script>
 
<template>
    <div>
        <div class="w-full flex flex-col items-center relative" style="height: 750px">
            <img :src="bg" style="width: 100%; height: 750px">
            <div class="absolute flex flex-col justify-center items-center">
                <img :src="logo" class="drop-shadow-2xl shadow-slate-950" style="width: 608px; height: 423px;">
                <input type="text" class="p-7 rounded-3xl mr-5 font-bold text-xl text-center"
                    style="height: 87px; background-color: #312807c8; width: 352px;" placeholder="Please enter your name."
                    v-model="username" @keydown.space.prevent>
                <p v-show="duplicatePop" class="mt-2 rounded-full bg-red-500 p-2 text-xl font-semibold">This username is already in use!</p>
                <button
                    class="btn rounded-full w-32 h-12 font-bold text-2xl text-yellow-200 bg-amber-900 hover:bg-amber-400"
                    style="margin-top: 38px;" @click="createUser" :disabled="!username || useBtn">
                    <router-link :to="{ name: 'Homepage' }">
                        PLAY</router-link>
                </button>
            </div>
        </div>
    </div>
</template>
 
<style scoped></style>
