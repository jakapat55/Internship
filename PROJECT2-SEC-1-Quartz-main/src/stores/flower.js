import { ref } from 'vue'
import { acceptHMRUpdate, defineStore } from 'pinia'
import { fetchFlower } from '../scripts/getMethod'

export const flowerStore = defineStore('flowernotify',()=>{
    const flower = ref([])
    const fetchAllFlower = async() => {
        flower.value = await fetchFlower()
    }
    const getAllFlower = () => {
        return flower.value
    }
    return {flower, fetchAllFlower, getAllFlower}
})

if(import.meta.hot){
    import.meta.hot.accept(acceptHMRUpdate(flowerStore, import.meta.hot))
}