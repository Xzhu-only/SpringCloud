import { ref } from 'vue'
import { defineStore } from 'pinia'

// 用户登录信息存储
export const useUserStore = defineStore('user', () => {
  const user = ref('')

  const setUser=(newUser)=>{
    user.value = newUser;
  }

  const deleteUser=()=>{
    user.value = ''
  }

  return { user, setUser, deleteUser}
})
