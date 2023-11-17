<script setup>
import { ref, onMounted, onBeforeUpdate } from "vue";
import { userStore } from "../stores/users";
import deleteUser from "../scripts/deleteUser";

const rawData = ref({
  id: "",
  name: "",
  score: "",
})
const haveData = ref(true);
const isClicked = ref(false);

const users = userStore()
const back = "/imgs/backBtn.svg"
const confirmPop = ref(false)
const idUser = ref()
const yourColor = ref()

const confirmDelete = (id) => {
  isClicked.value = true;
  if (isClicked.value === true) {
    confirmPop.value = true;
    idUser.value = id;
  }
};
const doDelete = async() => {
  deleteUser(idUser.value)
  rawData.value = users.user
  rawData.value.sort((a, b) => b.score - a.score);
  confirmPop.value = false
}
const doCancelDelete = () => {
  confirmPop.value = false
}

onMounted(async () => {
  await users.fetchAllUser()
  rawData.value = users.user
  rawData.value.sort((a, b) => b.score - a.score);
  console.log(isClicked.value)
});
</script>

<template>
  <div class="min-h-screen bg-bg-firstPage bg-cover w-full flex-shrink-0 flex flex-col items-center justify-center">
    <div class="bg-yellow-200 rounded-xl p-8 text-center w-[1051px] h-[547px]">
      <div class="bg-[#FED501] rounded-[50px] w-[30%] p-2 sticky left-[40%]">
        <h3 class="text-[#253057] font-semibold text-5xl">RANKING</h3>
      </div>
      <div class="rounded-xl h-2/3 p-4 text-center flex justify-center">
        <div class="table-container" style="width: 100%">
          <table style="width: 100%" class="text-[30px] text-[#253057] border-2 rounded-xl border-[#FED501]">
            <thead>
              <tr class="bg-[#FED501] border-2 border-[#FED501]">
                <th>Rank</th>
                <th> </th>
                <th>Name</th>
                <th>Score</th>
                <th></th>
              </tr>
            </thead>
            <tbody>
              <tr v-show="haveData" v-for="(data, index) in rawData" :key="index"
                :class="{ 'bg-yellow-300': index % 2 === 1 }">
                <td>{{ index + 1 }}</td>
                <td>
                  <svg xmlns="http://www.w3.org/2000/svg" :fill="data.color" :stroke="data.color" viewBox="0 0 24 24" stroke-width="1.5"
                    stroke="currentColor" class="w-10 h-10">
                    <path stroke-linecap="round" stroke-linejoin="round"
                      d="M15.75 6a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0zM4.501 20.118a7.5 7.5 0 0114.998 0A17.933 17.933 0 0112 21.75c-2.676 0-5.216-.584-7.499-1.632z" />
                  </svg>
                </td>
                <td>{{ data.name }}</td>
                <td>{{ data.score }}</td>
                <td>
                  <svg class="shake cursor-pointer" @click="confirmDelete(data.id)" xmlns="http://www.w3.org/2000/svg"
                    height="1em" viewBox="0 0 448 512">
                    <!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. -->
                    <path
                      d="M135.2 17.7C140.6 6.8 151.7 0 163.8 0H284.2c12.1 0 23.2 6.8 28.6 17.7L320 32h96c17.7 0 32 14.3 32 32s-14.3 32-32 32H32C14.3 96 0 81.7 0 64S14.3 32 32 32h96l7.2-14.3zM32 128H416V448c0 35.3-28.7 64-64 64H96c-35.3 0-64-28.7-64-64V128zm96 64c-8.8 0-16 7.2-16 16V432c0 8.8 7.2 16 16 16s16-7.2 16-16V208c0-8.8-7.2-16-16-16zm96 0c-8.8 0-16 7.2-16 16V432c0 8.8 7.2 16 16 16s16-7.2 16-16V208c0-8.8-7.2-16-16-16zm96 0c-8.8 0-16 7.2-16 16V432c0 8.8 7.2 16 16 16s16-7.2 16-16V208c0-8.8-7.2-16-16-16z" />
                  </svg>
                </td>
              </tr>
            </tbody>
          </table>
          <button value="Go back to home page">
            <RouterLink :to="{ name: 'Homepage' }"><img :src="back" class="absolute end-10 bottom-2 floating-img"
                style="margin-top: 40px;" />
            </RouterLink>
          </button>
        </div>
      </div>
    </div>
    <div v-if="confirmPop">
      <div class="popup">
        <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-screen h-screen bg-black opacity-60">
        </div>
      </div>
      <div class="popup">
        <div class="fixed top-1/2 left-1/2 -translate-x-1/2 -translate-y-1/2 w-1/3 h-48 bg-cyan-500 rounded-xl">
          <div class="top-10 flex flex-col items-center space-y-5">
            <p class="text-black text-center text-4xl mt-6">Are you sure you want to delete this rank?</p>
            <div class="flex flex-row space-x-5 text-xl">
              <button class="btn bg-green-500 text-green-950 border-green-950 hover:bg-green-700 hover:text-white"
                @click="doDelete">YES</button>
              <button class="btn bg-red-500 text-red-950 border-red-950 hover:bg-red-700 hover:text-white"
                @click="doCancelDelete">NO</button>
            </div>
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

.shake:hover {
  animation: shake 0.4s cubic-bezier(0.36, 2.57, 0.48, 1.13) both infinite;
}

@keyframes shake {

  0%,
  100% {
    transform: translateX(0) rotate(0deg);
  }

  25% {
    transform: translateX(-2px) rotate(-2deg);
  }

  50% {
    transform: translateX(2px) rotate(2deg);
  }

  75% {
    transform: translateX(-2px) rotate(-2deg);
  }
}

.table-container {
  height: 400px;
  /* Adjust this value as needed */
  overflow-y: auto;
  width: 100%;
  /* Maintain the original width */
}

/* Webkit (Chrome, Safari) scrollbar */
.table-container::-webkit-scrollbar {
  width: 12px;
}

.table-container::-webkit-scrollbar-thumb {
  background: #c68701;
  /* Change the color to match your theme */
  border-radius: 6px;
}

/* Firefox scrollbar */
.table-container {
  scrollbar-width: thin;
  scrollbar-color: #fed501 #f0f0f0;
  /* Change the colors to match your theme */
}
</style>
