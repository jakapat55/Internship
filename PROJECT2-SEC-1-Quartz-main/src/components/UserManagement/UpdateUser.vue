<script setup>
import { onBeforeUpdate, onMounted, ref } from 'vue';
import { useRouter, RouterLink } from 'vue-router'
import { userStore } from './../../stores/users.js'

const users = userStore()
const router = useRouter()
const useBtn = ref(false)
const id = ref()
const score = ref(0);
const existingScore = ref()
const oldColor = ref()
const yourColor = ref(oldColor)
const oldName = ref()
const newUsername = ref(oldName)
const successPop = ref(false)

onMounted(async () => {
    await users.fetchAllUser()
    id.value = parseInt(users.user[users.user.length - 1].id)
    existingScore.value = parseInt(users.user[users.user.length - 1].score)
    oldColor.value = users.user[users.user.length - 1].color
    oldName.value = users.user[users.user.length - 1].name
    if (newUsername.value == undefined) {
        useBtn.value = true
    }
})

onBeforeUpdate(() => {
    users.user.map((user) => {
        if (newUsername.value !== undefined) {
            useBtn.value = false
        }
        if (newUsername.value === user.name) {
            useBtn.value = true
        } if (newUsername.value == '') {
            useBtn.value = true
        }
        if (yourColor.value !== yourColor.value && newUsername.value !== undefined) {
            if(newUsername.value !== ''){
              useBtn.value = false  
            }
        }
    })
})

const updateUser = async()=> {
    const objData = {
        name: newUsername.value,
        color: yourColor.value,
        score: existingScore.value
    }
    await users.updateUserData(id.value, objData)
    const userEdited = objData
      users.user = users.user.map((data) => {
        if (data.id === userEdited.id) {
          data.name = userEdited.name;
          data.color = userEdited.color;
        }
        return data;
      });
    successPop.value = true
    setTimeout(()=>{
        successPop.value = false
        router.push({ name: 'Homepage' })
    },1000)
}
</script>
 
<template>
    <div>
        <div style="height: 750px;" class="flex justify-center h-full w-full bg-gradient-to-r from-purple-500 to-pink-500">
            <div class="border-4 rounded-full w-1/2 flex flex-col items-center mt-48"
                style="height: 300px; background-color: #4e05056e;">
                <div v-for="(data, index) in users.user" :key="index">
                    <div v-if="index === users.user.length - 1">
                        <div class="flex flex-row">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                                stroke="currentColor" class="w-24 h-24 fill-white mt-5 mb-2 iconcolor">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                    d="M15.75 6a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0zM4.501 20.118a7.5 7.5 0 0114.998 0A17.933 17.933 0 0112 21.75c-2.676 0-5.216-.584-7.499-1.632z" />
                            </svg>
                            <div class="flex flex-col mt-8 ml-2">
                                <p class="font-bold text-white text-lg">Select your color.</p>
                                <input type="color" class="mt-2 w-36 bg-white" style="height: 35px;" v-model="yourColor"
                                    :placeholder="data.color">
                            </div>
                        </div>
                        <input class="mt-2 mb-5 rounded-3xl text-center text-3xl text-white"
                            style="height: 80px; width: 300px; background-color: #453703b5;" v-model="newUsername"
                            :placeholder="data.name">
                        <div class="space-x-2 ml-4">
                            <button type="submit" @click="updateUser(data.id)"
                                class="btn rounded-full w-32 h-12 font-bold text-2xl mb-4 bg-green-400 hover:text-white text-green-950"
                                :disabled="useBtn">SAVE</button>
                            <button type="button"
                                class="btn bg-red-500 hover:text-white text-red-950 rounded-full w-32 h-12 font-bold text-2xl mb-4">
                                <RouterLink :to="{ name: 'Homepage' }">CANCEL</RouterLink>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div v-show="successPop">
            <div class="popup">
                <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-screen h-screen bg-black opacity-60">
                </div>
            </div>
            <div class="popup">
                <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-1/4 h-36 bg-green-500 rounded-xl">
                    <div class="top-10">
                        <p class="text-black text-center text-5xl mt-11">USER EDITED!</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
 
<style scoped>
.iconcolor {
    fill: v-bind(yourColor);
    stroke: v-bind(yourColor);
}
</style>