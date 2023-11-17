<script setup>
import { onMounted,ref,computed,onUpdated, onBeforeUpdate, watch } from 'vue';
import {getAllCategoey} from '../composable/getAllCatagories.js'
import { useRoute } from 'vue-router'
import { addAnnouncement } from '../composable/addAnnouncement.js'
import { getAnnouncementById } from '../composable/getAnnouncementById.js';
import {updateAnnouncement} from '../composable/updateAnnouncement.js' 
import {getDateTime,getClientTimeZone,editDateTime}from '../composable/dateTime.js'

const route = useRoute()
const chosenId = computed(() => route.params.id)  
let add = ref(true)
let edit = ref(false)
let pDate = ref(undefined)
let pTime  = ref (undefined)
let cDate = ref(undefined)
let cTime = ref(undefined)
let id = ref(undefined)
let announcementTitle = ref('')
let announcementCategory = ref('')
let announcementDescription = ref('')
let publishDate = ref('')
let closeDate = ref('')
let announcementDisplay = ref('')
let categoryId = ref(1)
let allCategories = ref([])
let singleAnnouncement 
let oTitle ,oCatId ,oDes ,opDate ,opTime ,ocDate ,ocTime ,oDisplay
let dtValidate = ref(false)
onMounted(async () =>   {
    allCategories.value = await getAllCategoey()
    if (chosenId.value !== undefined){
        add.value = false
        edit.value = true
        
        singleAnnouncement= await getAnnouncementById(chosenId.value,false)
        
        id.value = singleAnnouncement.id
        announcementTitle.value = singleAnnouncement.announcementTitle
        oTitle = singleAnnouncement.announcementTitle
        announcementCategory.value = singleAnnouncement.announcementCategory
        categoryId.value = allCategories.value.find((item) => item.categoryName === announcementCategory.value)?.categoryId;
        oCatId = allCategories.value.find((item) => item.categoryName === announcementCategory.value)?.categoryId;
        announcementDescription.value = singleAnnouncement.announcementDescription
        oDes = singleAnnouncement.announcementDescription.replace(/<[^>]+>/g, '');
        publishDate.value = singleAnnouncement.publishDate
        closeDate.value = singleAnnouncement.closeDate
        if(singleAnnouncement.announcementDisplay === 'Y'){
            announcementDisplay.value = true
            checkBox.value = true
            oDisplay = true
        }else if(singleAnnouncement.announcementDisplay === 'N'){
            announcementDisplay.value = false
            checkBox.value = false
            oDisplay = false
        }
        if (publishDate.value !== null ) {
            let pd = ISOToTime(publishDate.value)
            pDate.value = pd[0]
            opDate = pd[0]
            pTime.value = pd[1]
            opTime = pd[1]
        }
        if(closeDate.value !== null){
            let cd = ISOToTime(closeDate.value)
            cDate.value = cd[0]
            ocDate = cd[0]
            cTime.value = cd[1]
            ocTime = cd[1]
        }
    }
}
)
let checkBox = ref(false)
function displayNorY(){
    if (checkBox.value === true){
        announcementDisplay.value = 'Y'
    }else announcementDisplay.value = 'N'
}
function ISOToTime(iso){
    let datetime =editDateTime(iso)
    return datetime
}
function timeToISO(date,time){
    if (date !== undefined && time !== undefined){
    let dateTime = new Date(`${date}T${time}`);
    let ISO = dateTime.toISOString()
    return ISO  
    }
}
async function rawData(){
    displayNorY()
    announcementCategory.value = allCategories.value.find((item) => item.categoryId === categoryId.value)?.categoryName;
    if (categoryId.value === null){
            categoryId.value = singleAnnouncement.categoryId
        }
    let newAnnouncement = {
        "id": id.value,
        "announcementTitle": announcementTitle.value,
        "announcementCategory": announcementCategory.value,
        "announcementDescription": announcementDescription.value,
        "publishDate": publishDate.value,
        "closeDate": closeDate.value,
        "announcementDisplay": announcementDisplay.value,
        "categoryId": categoryId.value
    }
    if(chosenId.value === undefined){
        await addAnnouncement(newAnnouncement)
    }else {
        await updateAnnouncement(chosenId.value,newAnnouncement)
    } 
}
let submitButton = ref(true)


onBeforeUpdate(() => {
  titleError.value = ''
    descripError.value = ''
    announcementTitle.value.length === 200 ? titleError.value = 'Limit 200': ''
    let Des 
    Des = announcementDescription.value.replace(/<[^>]+>/g, '');
    if (Des.length >= 10000){
      alert('Limit 10000')
      if (oDes === undefined) {
        announcementDescription.value = ' '
      }else{
        announcementDescription.value = singleAnnouncement.announcementDescription
      }
      submitButton.value = true
    return
    }

  if (chosenId.value === undefined) {
    const titleValid = announcementTitle.value.trim() !== '';
    const categoryValid = categoryId.value !== '';
    const descriptionValid = Des.trim() !== '';
      submitButton.value = !(titleValid && categoryValid && descriptionValid && dtValidate.value);
  } else {
    const isValueChanged =
      oTitle !== announcementTitle.value ||
      oCatId !== categoryId.value ||
      oDes !== Des ||
      opDate !== pDate.value ||
      opTime !== pTime.value ||
      ocDate !== cDate.value ||
      ocTime !== cTime.value ||
      oDisplay !== checkBox.value;
    const titleValid = announcementTitle.value.trim() !== '';
    const descriptionValid = Des.trim() !== '';
  
      submitButton.value = !(titleValid && descriptionValid && isValueChanged && dtValidate.value);
  }
  dateTimeChecker()
});
let titleError = ref('')
let descripError = ref('')
let pError = ref('');
let cError = ref('');
let allError = ref('')
watch([pDate,pTime,cDate,cTime],dateTimeChecker)
function dateTimeChecker() {
  let pISO = null;
  let cISO = null;
  const toDay = new Date();


  if (pDate.value !== undefined && pTime.value !== undefined) {
    allError.value = ''
    pError.value = ''
    cError.value = ''

    if (pDate.value !== '' && pTime.value !== '') {
      pISO = new Date(timeToISO(pDate.value, pTime.value))
      if (pISO < toDay){
        pISO = null
        pError.value = 'The publishing time must be later than the current time.'
      }
    }else{
      publishDate.value = null;
      pISO = null;
    }
  }

  if (cDate.value !== undefined && cTime.value !== undefined) {
    cError.value = ''

    if (cDate.value !== '' && cTime.value !== '') {
      cISO = new Date(timeToISO(cDate.value, cTime.value))
      if (cISO < toDay){
        cISO = null
        cError.value = 'The closing time must be later than the current time.'
      }
    }else{
      closeDate.value = null;
      cISO = null;
    }
  }

  if (pDate.value === undefined || pDate.value === ''){
    if (cDate.value === undefined || cDate.value === ''){
      dtValidate.value = true
      return
    }
  }else if(cError.value !== '' || pError.value !== ''){
  dtValidate.value = false;
  }

  if (pError.value === '' && (cDate.value === undefined || cDate.value === '')){
    dtValidate.value = true;
    publishDate.value = pISO
    return
  }else dtValidate.value = false

  if (cError.value === '' && (pDate.value === undefined || pDate.value === '')){
    dtValidate.value = true;
    closeDate.value = cISO
    return
  }else dtValidate.value = false

    if (pISO !== null && cISO !== null) {
      if (cISO > pISO) {
          publishDate.value =timeToISO(pDate.value, pTime.value)
          closeDate.value = timeToISO(cDate.value, cTime.value)
          dtValidate.value = true
        } else if (cISO < pISO) {
          allError.value = 'The publishing time must be earlier than the closing time.'
          dtValidate.value = false
        }}}
</script>

<template>
    <div class="m-5 ">
        <div class="p-5 space-y-5 bg-orange-300 rounded-3xl ">
            <div>
                <h1 class="text-7xl">Announcement Detail:</h1>            
            </div>
            <div>
                <p class="font-medium text-3xl mb-2">Title</p>
                <input type="text" maxlength="200" class=" w-full rounded-full border ann-title border-gray-500 text-xl px-4 py-1" v-model="announcementTitle">
                <p>{{ titleError }}</p>
            </div>
            <div>
                <p class="font-medium text-3xl mb-2">Category</p>
                <select id="" v-model="categoryId" class="border ann-category border-gray-500 text-xl w-1/6 rounded-full block appearance-none px-4 py-1">
                    <option v-for="item of allCategories" :selected="1"  :value='item.categoryId' >{{item.categoryName}}</option>
                </select>
            </div>
            <div>
                <p class="font-medium text-3xl mb-2">Description</p>
                <!-- <textarea maxlength="10000" rows="20" class="ann-description w-full border border-gray-500 text-xl rounded-2xl px-4 py-1" v-model="announcementDescription"></textarea> -->
                <div  class=" bg-white ann-description w-full border border-gray-500 text-xl rounded-2xl">
                  <QuillEditor style="height: 400px
                  ;" toolbar="full" v-model:content="announcementDescription" theme="snow" contentType="html"/>
                </div>
                
                <p>{{ descripError }}</p>
            </div>
            <div>
                <p class="font-medium text-3xl mb-2">Publish Date</p>
                <div class="flex flex-row space-x-5 ">
                    <input type="date" class="border ann-publish-date border-gray-500 text-xl rounded-full px-10 py-1" v-model="pDate">
                    <input type="time" class="border ann-publish-time border-gray-500 text-xl rounded-full px-6 py-1" :disabled="!pDate"  v-model="pTime">
                    <p>{{ pError }}</p>
                </div>
            </div>
            <div>
                <p class="font-medium text-3xl mb-2">Close Date</p>
                <div class="flex flex-row space-x-5">
                    <input type="date" class="ann-close-date border border-gray-500 text-xl rounded-full px-10 py-1" v-model="cDate">
                    <input type="time" class="ann-close-time border border-gray-500 text-xl rounded-full px-6 py-1" :disabled="!cDate"  v-model="cTime">
                    <p>{{ cError }}</p>
                    <p class="text-xl">{{ allError }}</p>
                </div>
            </div>
            
            <div>
                <p class="font-medium text-3xl">Display</p>  
                <input type="checkbox" v-model="checkBox" class="ann-display">
                <label class="ml-2 text-xl">Check to show this announcement</label>
            </div>  
        </div>
    
        <div class="mt-5 flex flex-row space-x-3">  
                <router-link :to="{name:'all'}" v-if="add" class="ann-button hover:bg-red-400 transition duration-500 ease-in-out bg-white border-2 border-red-400 text-2xl rounded-full px-4 py-2">Cancel</router-link>
                <router-link :to="{name:'all'}" v-if="add">
                    <button class="ann-button transition duration-500 ease-in-out bg-white border-2 border-blue-500 text-2xl rounded-full px-4 py-2"
                        @click="rawData" :disabled="submitButton" :class="{ 'opacity-50': submitButton }"
                        v-bind:class="!submitButton ? ' hover:bg-blue-500 transition duration-500 ease-in-out;' : ''">
                        Submit
                    </button>
                </router-link>

                <router-link :to="{name:'description',params:{id:chosenId}}" v-if="edit" class="ann-button hover:bg-red-400 transition duration-500 ease-in-out bg-white border-2 border-red-400 text-2xl rounded-full px-4 py-2">Cancel</router-link>
                <router-link :to="{name:'description',params:{id:chosenId}}" v-if="edit">
                    <button class="ann-button transition duration-500 ease-in-out bg-white border-2 border-blue-500 text-2xl rounded-full px-4 py-2"
                        @click="rawData" :disabled="submitButton" :class="{ 'opacity-50': submitButton }"
                        v-bind:class="!submitButton ? ' hover:bg-blue-500 transition duration-500 ease-in-out;' : ''">
                        Submit
                    </button>
                </router-link>
            </div>
        </div>
</template>

<style scoped>

</style>
