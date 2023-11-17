import { defineStore, acceptHMRUpdate } from 'pinia'
import { ref } from 'vue'
export const announcer = defineStore('activeAndClose',()=>{
    const setMode = ref('active')
    const setCategory = ref()
    const setPage = ref(0)
    return {setMode, setCategory, setPage }
})

if (import.meta.hot) {
    import.meta.hot.accept(acceptHMRUpdate(announcer, import.meta.hot))
}