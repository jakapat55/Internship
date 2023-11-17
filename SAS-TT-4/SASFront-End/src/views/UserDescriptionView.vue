<script setup>
import { getAnnouncementById } from '../composable/getAnnouncementById.js'
import { ref,computed,onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getDateTime,getClientTimeZone } from '../composable/dateTime.js'
import { announcer } from '../stores/mode.js'

const route = useRoute()
const chosenId = computed(() => route.params.id)  
let userAnnouncer = announcer()
let closeDate = ref(false)
let singleAnnouncement = ref([])
let alertStatus = ref(true)
onMounted( async () => {  

    singleAnnouncement.value = await getAnnouncementById(chosenId.value,true) 
    if (singleAnnouncement.value === undefined){
    alertStatus.value = false
    alert('The request page is not available');
    window.location.href = "/tt4/announcement";
    return
    }
    getClientTimeZone()
    let pbd = singleAnnouncement.value.publishDate
    let cd = singleAnnouncement.value.closeDate
    singleAnnouncement.value.publishDate = getDateTime(pbd)
    singleAnnouncement.value.closeDate = getDateTime(cd)
    if (userAnnouncer.setMode === 'close'){
        closeDate.value = true
    }
    if (userAnnouncer.setMode === 'active'){
        closeDate.value = false
    }
})

</script>

<template>
    <div class="m-5 ">
        <div class="p-3 space-y-5 bg-orange-300 rounded-3xl ">
            <div v-if="alertStatus">
                <div class=" bg-white rounded-2xl py-2 mx-5 space-y-5 my-5">        
                    <div class="flex flex-row w-full">
                    <p class="flex text-2xl pt-4 items-center px-5"><span class="ann-title pl-5 text-4xl font-bold">{{ singleAnnouncement.announcementTitle }}</span></p>
                    <!-- <p class="flex text-2xl pt-4 items-center px-5 ml-auto"><span class="ann-counter pl-5 text-2xl font-bold">View : {{ singleAnnouncement.viewCount }}</span></p> -->
                    </div>   
                    <p class="flex text-2xl items-center px-5"><span class="ann-category pl-5 text-gray-400"> {{ singleAnnouncement.announcementCategory }}</span></p>
                    <div class="flex items-center px-5 ">
                        <span class=" w-1/12 text-2xl ">Description </span>
                        <div class="ann-description pl-5 ql-editor" v-html="singleAnnouncement.announcementDescription"></div>
                    </div>
                    <p v-show="closeDate" class="flex text-2xl items-center px-5"><span class="text-red-500 pl-5">Closed on: </span><span class="ann-close-date pl-5">{{ singleAnnouncement.closeDate }}</span> </p>
                </div>
                
            </div>
        </div>
        <div class="flex flex-row space-x-3 my-5">  
            <router-link :to="{name:'userAll'}" class="ann-button hover:bg-red-400 transition duration-500 ease-in-out bg-white border-2 border-red-400 text-2xl rounded-full px-4 py-2">Back</router-link>
        </div> 
    </div>
</template>

<style scoped>
</style>
