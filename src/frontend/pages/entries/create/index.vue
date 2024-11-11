<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
</script>

<template>
  <div>
    <MainShell title="New Entry" path="Entries">
      <div class="flex justify-end -mt-6">
        <NuxtLink to="/entries">
          <button type="button">Back to Entries</button>
        </NuxtLink>
      </div>
      <input type="text" class="block w-full border-0 h-10 my-3 pl-2 rounded-md ring-1 ring-inset ring-gray-300 focus:ring-2 focus:ring-inset focus:ring-indigo-600" placeholder="Entry Title" />
      <textarea
          name="entry"
          id="entry"
          placeholder="Entry Text"
          class="block w-full h-96 rounded-md text-xl border-0 py-1.5 text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-400 focus:ring-2 focus:ring-inset focus:ring-indigo-600 pl-2 resize-none"/>
    </MainShell>
  </div>
</template>

<style scoped>

</style>