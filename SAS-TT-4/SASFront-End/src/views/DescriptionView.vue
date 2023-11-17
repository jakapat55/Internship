<script setup>
import { getAnnouncementById } from '../composable/getAnnouncementById.js'
import { ref,computed,onMounted } from 'vue'
import { useRoute } from 'vue-router'
import { getDateTime,getClientTimeZone } from '../composable/dateTime.js'
const route = useRoute()
const chosenId = computed(() => route.params.id)  

let  singleAnnouncement = ref([])
let alertStatus = ref(true)
onMounted( async () => {  

    singleAnnouncement.value = await getAnnouncementById(chosenId.value,false) 
    if (singleAnnouncement.value === undefined){
    alertStatus.value =false
    alert('The request page is not available');
    window.location.href = "/tt4/admin/announcement";
    return
    }
    getClientTimeZone()
    let pbd = singleAnnouncement.value.publishDate
    let cd = singleAnnouncement.value.closeDate
    singleAnnouncement.value.publishDate = getDateTime(pbd)
    singleAnnouncement.value.closeDate = getDateTime(cd)

})
</script>

<template>
    <div class="m-5 ">
        <div class="p-3 space-y-5 bg-orange-300 rounded-3xl ">
        <div class="m-5 flex flex-row w-full">
            <h1 class="text-7xl">Announcement Detail:</h1>
            <p class="flex text-2xl pt-4 items-center px-5 ml-auto mr-5 "><span class="ann-counter pl-5 text-2xl font-bold">View : {{ singleAnnouncement.viewCount }}</span></p>
        </div>
        <div v-if="alertStatus">
            <div class=" bg-white rounded-2xl py-2 mx-5 space-y-5 mb-5">   
                <p class="flex text-2xl pt-4 items-center px-5"><span class=" w-1/12">Title </span><span class="ann-title pl-5">{{ singleAnnouncement.announcementTitle }}</span></p>
                <p class="flex text-2xl items-center px-5"><span class=" w-1/12">Category </span><span class="ann-category pl-5"> {{ singleAnnouncement.announcementCategory }}</span></p>
                <div class="flex items-center px-5 ">
                    <span class=" w-1/12 text-2xl ">Description </span>
                    <div class="ann-description pl-5 ql-editor" v-html="singleAnnouncement.announcementDescription"></div>
                </div>
                <p class="flex text-2xl items-center px-5"><span class=" w-1/12">Publish Date</span><span class="ann-publish-date pl-5"> {{ singleAnnouncement.publishDate }}</span></p>
                <p class="flex text-2xl items-center px-5"><span class=" w-1/12">Close Date</span><span class="ann-close-date pl-5">{{ singleAnnouncement.closeDate }}</span> </p>
                <p class="flex text-2xl pb-4 items-center px-5"><span class=" w-1/12">Display</span><span class="ann-display pl-5" :class="singleAnnouncement.announcementDisplay === 'Y' ? 'text-green-600':'text-red-600'">{{ singleAnnouncement.announcementDisplay }}</span></p>
            </div>
            
        </div>
    </div>
    <div class="flex flex-row space-x-3 my-5">  
                <router-link :to="{name:'all'}" class="ann-button hover:bg-red-400 transition duration-500 ease-in-out bg-white border-2 border-red-400 text-2xl rounded-full px-4 py-2">Back</router-link>
                <router-link :to="{name:'edit',params:{id:chosenId}}" class="ann-button hover:bg-orange-400 transition duration-500 ease-in-out bg-white border-2 border-orange-400 text-2xl rounded-full px-4 py-2">Edit</router-link>     
            </div> 
    </div>
</template>

<style scoped>
</style>
