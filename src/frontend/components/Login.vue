<!-- components/Login.vue -->
<template>
  <div class="flex flex-col items-center justify-center space-y-4 p-4">
    <h2 class="text-2xl font-bold mb-4">Login</h2>

    <!-- Google Login Button -->
    <button
        @click="loginWithProvider('google')"
        class="flex items-center justify-center space-x-2 bg-white text-gray-800 font-semibold py-2 px-4 border border-gray-300 rounded-lg shadow-sm hover:bg-gray-50 w-64"
    >
<!--      <img src="/google-icon.svg" alt="Google" class="w-5 h-5" />-->
      <span>Continue with Google</span>
    </button>

    <!-- GitHub Login Button -->
    <button
        @click="loginWithProvider('github')"
        class="flex items-center justify-center space-x-2 bg-gray-900 text-white font-semibold py-2 px-4 border border-gray-800 rounded-lg shadow-sm hover:bg-gray-800 w-64"
    >
<!--      <img src="/github-icon.svg" alt="GitHub" class="w-5 h-5" />-->
      <span>Continue with GitHub</span>
    </button>
  </div>
</template>

<script setup>
const config = useRuntimeConfig()

const loginWithProvider = (provider) => {
  // Redirect to your backend's OAuth2 login endpoint
  window.location.href = `http://localhost:8080/oauth2/authorization/${provider}`
}
</script>
