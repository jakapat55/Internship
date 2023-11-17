import { createRouter, createWebHistory } from "vue-router";
import Homepage from "../components/Homepage.vue"
import Questions from "../components/Game/Questions.vue"
import Mode from "../components/Game/Mode.vue"
import Category from "../components/Game/Category.vue"
import CreateUser from "../components/UserManagement/CreateUser.vue"
import UpdateUser from '../components/UserManagement/UpdateUser.vue'
import Ranking from '../components/Ranking.vue'
 
const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/homepage',
            name: 'Homepage',
            component: Homepage
        },
        {
            path: '/',
            name: 'CreateUser',
            component: CreateUser
        },
        {
            path: '/update',
            name: 'UpdateUser',
            component: UpdateUser
        },
        {
            path: '/question/:category/:mode',
            name: 'Questions',
            component: Questions
        },
        {
            path: '/mode/:category',
            name: 'mode',
            component: Mode
        },
        {
            path: '/category',
            name: 'category',
            component: Category
        },
        {
            path: '/ranking',
            name: 'ranking',
            component: Ranking
        },
    ]
})
 
export default router