<script setup>
import { computed, ref } from "vue";
import { useRoute } from 'vue-router'
const { params } = useRoute()
const selectedCategory = ref(params)
const currentCategory = computed(()=>{
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
const home = "/imgs/home-btn.svg"
const back = "/imgs/backBtn.svg"
</script>
 
<template>
  <div>
    <div v-if="currentCategory === 'fruit'" class="bgFruit bg-cover w-full flex flex-col items-center" style="height: 760px">
      <div class="select-mode">
        <p
                style="margin-top: 70px; font-size: 90px; background-color: #F6BCBC; height: 137px; width: 660px; text-align: center; color: #664E4E; border-radius: 60px;">
                SELECT MODE</p>
          <div class="button-row">
            <router-link :to="{ name: 'Questions', params: { category: 'fruit', mode: 'easy' } }">
              <p style=" font-size: 60px; background-color: #A9FF3C; width: 300px; text-align: center; border-radius: 60px; color: #253057;margin-top: -50px;"
              class="ring-6 ring-black/20 hover:drop-shadow-2xl"
              >Easy</p>
            </router-link>
              <router-link :to="{ name: 'Questions', params: { category: 'fruit', mode: 'normal' } }">
                <p style=" font-size: 60px; background-color: #F6E276; width: 300px; text-align: center; border-radius: 60px; color: #253057;"
                class="ring-6 ring-black/20 hover:drop-shadow-2xl"
              >Normal</p>
              </router-link>
              <router-link :to="{ name: 'Questions', params: { category: 'fruit', mode: 'hard' } }">
                <p style=" font-size: 60px; background-color: #F24545; width: 300px; text-align: center; border-radius: 60px; color: #253057;"
                class="ring-6 ring-black/20 hover:drop-shadow-2xl"
              >Hard</p>
              </router-link>
          </div>
      </div>
    </div>
    <div v-if="currentCategory==='vegie'" class="bgVegie bg-cover w-full flex flex-col items-center" style="height: 760px">
      <div class="select-mode">
        <p
                style="margin-top: 70px; font-size: 90px; background-color: #F6BCBC; height: 137px; width: 660px; text-align: center; color: #664E4E; border-radius: 60px;">
                SELECT MODE</p>
        <div class="button-row">
          <router-link :to="{ name: 'Questions', params: { category: 'vegie', mode: 'easy' } }">
            <p style=" font-size: 60px; background-color: #A9FF3C; width: 300px; text-align: center; border-radius: 60px; color: #253057;margin-top: -50px;"
              class="ring-6 ring-black/20 hover:drop-shadow-2xl"
              >Easy</p>            
          </router-link>
          <router-link :to="{ name: 'Questions', params: { category: 'vegie', mode: 'normal' } }">
            <p style=" font-size: 60px; background-color: #F6E276; width: 300px; text-align: center; border-radius: 60px; color: #253057;"
                class="ring-6 ring-black/20 hover:drop-shadow-2xl"
              >Normal</p>
          </router-link>
          <router-link :to="{ name: 'Questions', params: { category: 'vegie', mode: 'hard' } }">
            <p style=" font-size: 60px; background-color: #F24545; width: 300px; text-align: center; border-radius: 60px; color: #253057;"
                class="ring-6 ring-black/20 hover:drop-shadow-2xl"
              >Hard</p>
          </router-link>
        </div>
      </div>      
    </div>
    <div v-if="currentCategory==='flower'" class="bgFlower bg-cover w-full flex flex-col items-center" style="height: 760px">
      <div class="select-mode">
        <p
                style="margin-top: 70px; font-size: 90px; background-color: #F6BCBC; height: 137px; width: 660px; text-align: center; color: #664E4E; border-radius: 60px;">
                SELECT MODE</p>
        <div class="button-row">
          <router-link :to="{ name: 'Questions', params: { category: 'flower', mode: 'easy' } }">
            <p style=" font-size: 60px; background-color: #A9FF3C; width: 300px; text-align: center; border-radius: 60px; color: #253057;margin-top: -50px;"
              class="ring-6 ring-black/20 hover:drop-shadow-2xl"
              >Easy</p>
          </router-link>
          <router-link :to="{ name: 'Questions', params: { category: 'flower', mode: 'normal' } }">
            <p style=" font-size: 60px; background-color: #F6E276; width: 300px; text-align: center; border-radius: 60px; color: #253057;"
                class="ring-6 ring-black/20 hover:drop-shadow-2xl"
              >Normal</p>
          </router-link>
          <router-link :to="{ name: 'Questions', params: { category: 'flower', mode: 'hard' } }">
            <p style=" font-size: 60px; background-color: #F24545; width: 300px; text-align: center; border-radius: 60px; color: #253057;"
                class="ring-6 ring-black/20 hover:drop-shadow-2xl"
              >Hard</p>
          </router-link>
        </div>
      </div>
    </div>
    <button value="Go back to home page">
            <RouterLink :to="{ name: 'Homepage' }">
                <img :src="home" 
                    style="margin-top: -140px; position: absolute; inset-inline-end: 20px;"
                    class=" floating-img">
            </RouterLink>
    </button>
    <button value="Go back to previous page">
            <RouterLink :to="{ name: 'category' }">
                <img :src="back" 
                    style="margin-top: -165px; position: absolute;"
                    class=" floating-img">
            </RouterLink>
    </button>
  </div>
</template>

 
<style scoped>
.select-mode {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  height: 100vh;
}

.button-row {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
  flex-grow: 1;
  justify-content: center;
}
.bgFruit{
background-image: url(/imgs/bg-fruit-mode.svg)
}
.bgVegie{
background-image: url(/imgs/bg-vegie-mode.svg);
}
.bgFlower{
background-image: url(/imgs/bg-flower-mode.svg);
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