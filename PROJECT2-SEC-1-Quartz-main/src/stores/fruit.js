import { ref } from 'vue'
import { acceptHMRUpdate, defineStore } from 'pinia'
import { fetchFruit } from '../scripts/getMethod'

export const fruitStore = defineStore('fruitnotify',()=>{
    const fruit = ref([])
    const fetchAllFruit = async() => {
        fruit.value = await fetchFruit()
    }
    const getAllFruit = () => {
        return fruit.value
    }
    return {fruit, fetchAllFruit, getAllFruit}
})

if(import.meta.hot){
    import.meta.hot.accept(acceptHMRUpdate(fruitStore, import.meta.hot))
}