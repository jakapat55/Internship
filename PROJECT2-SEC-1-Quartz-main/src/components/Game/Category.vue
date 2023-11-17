<script setup>
import { ref } from 'vue';
import { useRouter, RouterLink } from 'vue-router'

const router = useRouter()
const btnid = ref()
const btnid2 = ref()
const btnid3 = ref()
const home = "./imgs/home-btn.svg"


const nextPage = (id) => {
    if (id.id==='fruit') {
        router.push(`/mode/${id.id}`)
    }
    if (id.id==='vegie') {
        router.push(`/mode/${id.id}`)
    }
    if (id.id==='flower') {
        router.push(`/mode/${id.id}`)
    }
}

</script>
 
<template>
    <div class="bgmode bg-cover w-full flex flex-col items-center" style="height: 750px">
        <div>
            <p
                style="margin-top: 70px; font-size: 90px; background-color: #4ED0A9; height: 137px; width: 860px; text-align: center; color: white; border-radius: 60px;">
                SELECT CATEGORY</p>
        </div>
        <div class="flex flex-row space-x-72"
            style="font-size: 55px; text-align: center; margin-top: 75px; font-weight: 500;">
            
            <button ref="btnid" id="fruit" @click="nextPage(btnid)">
                <p style="width: 223px; height: 209px; background-color: #98FFFC; color: #23615F; padding-top: 60px; border-radius: 30px;"
                    class="ring-8 ring-black/20 hover:drop-shadow-2xl">
                    FRUIT</p>
            </button>

            <button ref="btnid2" id="vegie" @click="nextPage(btnid2)">
                <p style="width: 223px; height: 209px; background-color: #FDEA87; color: #73621B; padding-top: 60px; border-radius: 30px;"
                    class="ring-8 ring-black/20 hover:drop-shadow-2xl">
                    VEGIE</p>
            </button>

            <button ref="btnid3" id="flower" @click="nextPage(btnid3)">
                <p style="width: 223px; height: 209px; background-color: #C8EDFF; color: #0F3548; padding-top: 60px; border-radius: 30px"
                    class="ring-8 ring-black/20 hover:drop-shadow-2xl">
                    FLOWER</p>
            </button>
        </div>
        <button value="Go back to home page">
            <RouterLink :to="{ name: 'Homepage' }">
                <img :src="home" 
                    style="margin-top: 150px; position: absolute; inset-inline-end: 30px;"
                    class=" floating-img">
            </RouterLink>
        </button>
    </div>
</template>
 
<style scoped>
p {
    font-family: system-ui, -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto,
        Oxygen, Ubuntu, Cantarell, "Open Sans", "Helvetica Neue", sans-serif;
}
.bgmode{
    background-image: url('/imgs/bg-mode.svg');
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