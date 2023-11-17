<script setup>
import { getAllAnnouncement } from '../composable/getAllAnnouncements.js'
import { onMounted,ref } from 'vue';
import { getDateTime,getClientTimeZone } from '../composable/dateTime.js' 
import {deleteAnnouncement} from '../composable/deleteAnnouncement.js'
let AllAnnouncement = ref([])
let isEmpty = ref([])
let isEmptyMessage = ref('')
let clientTimeZone
onMounted( async () => {
    AllAnnouncement.value = await getAllAnnouncement("admin")
    clientTimeZone = getClientTimeZone()
    if (AllAnnouncement.value.length === 0){
      isEmpty.value = false
      isEmptyMessage.value = 'No Announcement'
    }
    for(let i = 0 ; i < AllAnnouncement.value.length; i++){
      let pbd = AllAnnouncement.value[i].publishDate
      let cd = AllAnnouncement.value[i].closeDate
      AllAnnouncement.value[i].publishDate = getDateTime(pbd)
      if(AllAnnouncement.value[i].publishDate === undefined){
        AllAnnouncement.value[i].publishDate = '-'
      }
      AllAnnouncement.value[i].closeDate = getDateTime(cd)
      if (AllAnnouncement.value[i].closeDate === undefined) {
        AllAnnouncement.value[i].closeDate = '-'
      }
    }
  })
let deleteMessage = ref('')
async function deleteConfirm(id){
  if(confirm("Do you want to delete this Announcement")){
    deleteMessage.value =  await deleteAnnouncement(id)
    if(deleteMessage.value !== true ){
      alert(deleteMessage.value.message)
      location.reload()
    }
  }
}
</script>

<template>
  <div class="m-4">
      <div class=" text-black  pt-10 pb-5 space-y-5">
          <h1 class="text-center text-5xl font-bold">SIT Announcement System (SAS)</h1>
          <div class="flex flex-row">
              <div>
                <p class="text-2xl font-bold">Date/Time Shown in Timezone:<span class=" text-orange-600">{{ clientTimeZone }}</span></p>
              </div>
              <div class="ml-auto">
                <router-link :to="{name:'add'}" class="ann-button hover:bg-blue-400 transition duration-500 ease-in-out bg-white border-2 border-blue-400 text-2xl rounded-full px-4 py-2">Add Announcement</router-link>
              </div> 
              
          </div>
      </div>
      <div>
          <table class=" w-full">       
            <tr class="text-black  text-3xl bg-orange-400 font-bold border border-orange-500 ">
              <th class=" py-8 px-3 ">No.</th>
              <th>Title</th>
              <th>Category</th>
              <th>Publish Date</th>
              <th>Close Date</th>
              <th>Display</th>
              <th>View</th>
              <th>Action</th>
            </tr>
            <tr v-show="isEmpty" v-for="(item,index) of AllAnnouncement" :key="index" :class="index%2 ? ' bg-orange-100':''" class="ann-item py-2 text-xl w-full text-center border border-gray-500" >
              <td class="py-5 px-1">{{index+1}}</td>
              <td class="ann-title text-left">{{item.announcementTitle}}</td>
              <td class="ann-category text-center">{{item.announcementCategory}}</td>
              <td class="ann-publish-date">{{item.publishDate}}</td>
              <td class="ann-close-date">{{item.closeDate}}</td>
              <td class="ann-display font-bold" :class="item.announcementDisplay === 'Y' ? 'text-green-600':'text-red-600'">{{item.announcementDisplay}}</td>
              <td class="viewer">{{ item.viewCount }}</td>
              <td class="flex felx-row m-3 justify-center space-x-3">
                <router-link :to="{name:'description',params:{id:item.id}}" class="ann-button hover:bg-green-400 transition duration-500 ease-in-out bg-white border-2 border-green-400 text-2xl rounded-full px-4 py-2">View</router-link>
                <button class="ann-button hover:bg-red-400 transition duration-500 ease-in-out bg-white border-2 border-red-400 text-2xl rounded-full px-4 py-2" @click="deleteConfirm(item.id)">Delete</button>
              </td>
            </tr> 
          </table>
      </div>
      <div v-show="!isEmpty" class="pt-20">
          <p class="w-screen text-5xl text-red-400 text-center">{{ isEmptyMessage }}</p>
      </div>
  </div>
</template>
<style scoped>
</style>
