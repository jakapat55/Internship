import { ref } from 'vue'
import { acceptHMRUpdate, defineStore } from 'pinia'
import { fetchVegie } from '../scripts/getMethod'

export const vegieStore = defineStore('vegienotify',()=>{
    const vegie = ref([])
    const fetchAllVegie = async() => {
        vegie.value = await fetchVegie()
    }
    const getAllVegie = () => {
        return vegie.value
    }
    return {vegie, fetchAllVegie, getAllVegie}
})

if(import.meta.hot){
    import.meta.hot.accept(acceptHMRUpdate(vegieStore, import.meta.hot))
}