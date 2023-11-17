import { createRouter, createWebHistory } from 'vue-router'
import DescriptionView from '../views/DescriptionView.vue'
import AllAnnouncement from '../components/AllAnnouncement.vue'
import AddEditAnnouncementView from '../views/AddEditAnnouncementView.vue'
import UserAnnouncement from '../components/UserAnnouncement.vue'
import UserDescriptionView from '../views/UserDescriptionView.vue'
import NotFound from '../views/NotFoundView.vue'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/admin/announcement/:id',
      name: 'description',
      component: DescriptionView
    },
    {
      path: '/admin/announcement',
      name: 'all',
      component: AllAnnouncement
    },
    {
      path: '/admin/announcement/',
      name: 'delete',
      component: AllAnnouncement
    },
    {
      path: '/admin/announcement/add',
      name: 'add',
      component: AddEditAnnouncementView
    },
    {
      path: '/admin/announcement/:id/edit',
      name: 'edit',
      component: AddEditAnnouncementView
    },
    {
      path: '/announcement',
      name: 'userAll',
      component: UserAnnouncement
    },
    {
      path: '/announcement/:id',
      name: 'userDescription',
      component: UserDescriptionView
    },
    {
      path: '/:catchNotMathPath(.*)',
      name: 'NotFound',
      component: NotFound
    }
  ]
})

export default router