<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
const {data: tags, refresh} = await useFetch("http://localhost:8080/tags");

const tagName = ref("");

async function handleSubmit(event) {
  event.preventDefault();

  await $fetch("http://localhost:8080/tags", {
    method: "POST",
    body: {
      tagName: tagName.value,
    }
  });

  tagName.value = "";
  await refresh();
}

</script>

<template>
  <MainShell
      title="Tags"
      :path="$route.path">
    <form @submit="handleSubmit" class="flex max-w-xs gap-2 mb-3">
      <input v-model="tagName" placeholder="Name" class="pl-1 border-2 rounded">
      <button type="submit" class="bg-blue-500 text-white p-1 rounded">Submit</button>
    </form>
    <TagsListView :tags="tags"/>
  </MainShell>
</template>

<style scoped>

</style>