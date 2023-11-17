<script setup>
import { getAllAnnouncement } from '../composable/getAllAnnouncements.js'
import { onMounted,ref, watch } from 'vue';
import { getDateTime,getClientTimeZone } from '../composable/dateTime.js' 
import { getAllCategoey } from '../composable/getAllCatagories';
import { announcer } from '../stores/mode.js'
let userAnnouncer = announcer()
let AllAnnouncement = ref([])
let isEmpty = ref([])
let isEmptyMessage = ref('')
let clientTimeZone
let modeShow= ref(false)
let allCategories = ref([])
let totalPageArr = ref([0])
let page = ref(0)
let totalPage = ref()
async function getInfo() {
 page.value = userAnnouncer.setPage
  allCategories.value = await getAllCategoey()
    AllAnnouncement.value = await getAllAnnouncement(userAnnouncer.setMode,userAnnouncer.setCategory,page.value)
    totalPage.value = AllAnnouncement.value.totalPages
    totalPageArr.value=[]
    for (let i = 0 ; i< totalPage.value ; i++){totalPageArr.value.push(i)}
      if (totalPageArr.value.length > 10) {
        let start = 0;
        let end = start + 10;
      if ( page.value >= 10){
          start = page.value-9
          end = page.value+1
        }
        totalPageArr.value = totalPageArr.value.slice(start, end);
    }
    AllAnnouncement.value = AllAnnouncement.value.content
    clientTimeZone = getClientTimeZone()
    if (AllAnnouncement.value.length === 0){
      isEmpty.value = false
      isEmptyMessage.value = 'No Announcement'
    } if (AllAnnouncement.value.length > 0){
      isEmpty.value = true
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
    if (userAnnouncer.setMode === 'close'){
      buttonWord.value = 'Active Announcements'
      modeShow.value = true
      buttonMode.value = false
    }   
    if (userAnnouncer.setMode === 'ative'){
      buttonWord.value = 'Closed Announcements'
      modeShow.value = false
      buttonMode.value = true
    }
    categoryId.value= userAnnouncer.setCategory 
  }
let buttonMode = ref(true)
let buttonWord = ref('Closed Announcements')
let categoryId = ref(1)
onMounted(getInfo )

async function getClose() {
  if (buttonMode.value === false) {
    userAnnouncer.setMode = 'close';
    modeShow.value = true;
    totalPageArr.value = []
    page.value = 0
  }
  if (buttonMode.value === true) {
    userAnnouncer.setMode = 'active';
    buttonWord.value = 'Closed Announcements';
    modeShow.value = false;
    totalPageArr.value = []
    page.value = 0
  }
  await getInfo();
}
watch(buttonMode,()=>{
  getClose()
  page.value = 0
  userAnnouncer.setPage = 0
})
watch(categoryId,()=>{
  categoryId.value === userAnnouncer.setCategory? '':userAnnouncer.setPage = 0
  userAnnouncer.setCategory = categoryId.value
  page.value = 0
  getInfo()
})
watch(page,()=>{
  userAnnouncer.setPage = page.value
  getInfo()})
</script>

<template>
    <div class="m-4">
      <div class=" text-black  pt-10 pb-5 space-y-5">
          <h1 class="text-center text-5xl font-bold">SIT Announcement System (SAS)</h1>
          <div class="flex flex-row">
              <div>
                <p class="text-2xl font-bold">Date/Time Shown in Timezone: <span class=" text-orange-600">{{ clientTimeZone }}</span></p>
              </div>
          </div>
      </div>
      

        <div class="flex flex-row mb-5">
                <div>
                    <p class="font-medium text-2xl ">Choose Category :</p>
                </div>
                    <select id="" v-model="categoryId" class="border ann-category-filter border-gray-500 text-xl w-1/6 rounded-full block appearance-none mx-1 px-4 py-1">
                        <option value=-1>ทั้งหมด</option>
                        <option v-for="item of allCategories" :value='item.categoryId' >{{item.categoryName}}</option>
                    </select>
                <div class="ml-auto">
                    <buttton @click="buttonMode = !buttonMode" class="ann-button hover:bg-blue-400 transition duration-500 ease-in-out bg-white border-2 border-blue-400 text-2xl rounded-full px-4 py-2">{{ buttonWord }}</buttton>
                </div> 
        </div>


      <div>
          <table class=" w-full">
            <tr class="text-black text-3xl bg-orange-400 font-bold border border-orange-500 ">
              <th class=" py-8 px-3 ">No.</th>
              <th>Title</th>
              <th v-show="modeShow">Close Date</th>
              <th>Category</th>
            </tr>
            <tr v-show="isEmpty" v-for="(item,index) of AllAnnouncement" :key="index" :class="index%2 ? ' bg-orange-100':''" class="ann-item py-2 text-xl w-full text-center border border-gray-500" >
              <td class="py-5 px-1">{{(page*5)+(index+1)}}</td>
              <!-- <td class="py-5 px-1">{{item.id}}</td> -->
              <td class="ann-title text-left hover:text-orange-600" ><router-link :to="{name:'userDescription',params:{id:item.id}}">{{item.announcementTitle}}</router-link></td>
              <td v-show="modeShow" class="ann-close-date">{{item.closeDate}}</td>
              <td class="ann-category text-center">{{item.announcementCategory}}</td>  
            </tr> 
          </table>          
      </div>

      <div class="my-5 text-center" v-if="totalPage > 1">
        <button class="ann-page-prev border  p-2 px-8 border-none  bg-orange-500 rounded-l-lg" :disabled="page === 0" :class="{ 'opacity-50': page === 0 }" @click="page--" v-bind:class="!submitButton ? ' hover:bg-orange-400 transition duration-500 ease-in-out;' : ''">Prev</button>
        <button class="border py-2 border-none  px-6 bg-orange-200 hover:bg-orange-300 transition duration-500 ease-in-out;" v-for="(item,index) of totalPageArr" :key="index" @click="page=item" :class="{' bg-orange-400': page === item }"  v-bind:class="`ann-page-${index}`">{{ item+1 }}</button>
        <button class="ann-page-next border border-none p-2 px-8 bg-orange-500 rounded-r-lg" 
        :disabled="page+1 === totalPage" @click="page++" :class="{ 'opacity-50': page+1 === totalPage}" v-bind:class="!submitButton ? ' hover:bg-orange-400 transition duration-500 ease-in-out;' : ''">Next</button>
      </div>
      <div v-show="!isEmpty" class="pt-20">
          <p class="w-screen text-5xl text-red-400 text-center">{{ isEmptyMessage }}</p>
      </div>
    </div>
</template>
<style scoped>
</style>
