import {ref} from 'vue'
import { fetchUser, fetchUserById  } from '../scripts/getMethod'
import { updateUser } from '../scripts/updateUser'
import { acceptHMRUpdate, defineStore } from 'pinia'

export const userStore =  defineStore('usernotify',() => {
    const user = ref([])
    const fetchAllUser = async() => {
        user.value = await fetchUser()
    }
    const getAllUser = () => {
        return user.value
    }
    const updateUserData = async(id, objData) => {
        await updateUser(id, objData)
    }
    const getUserById = async(id) => {
        return await fetchUserById(id)
    }
    return {user,getAllUser,fetchAllUser, updateUserData, getUserById}
})


if(import.meta.hot){
    import.meta.hot.accept(acceptHMRUpdate(userStore, import.meta.hot))
}