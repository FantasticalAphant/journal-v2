<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
const {data: tags} = await useFetch("http://localhost:8080/tags");

const tagName = ref("");

async function handleSubmit(event) {
  event.preventDefault();

  await useFetch("http://localhost:8080/tags", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      tagName: tagName.value,
    })
  });

  journalName.value = "";
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
    <pre>{{tags}}</pre>
    <div v-if="tags">
      <ul>
        <li v-for="tag in tags" :key="tag.tagId">
          <NuxtLink class="text-blue-600" :to="`/tags/${tag.tagId}`">
            Name: {{ tag.tagName }}
          </NuxtLink>
          <p>Entries: {{ JSON.stringify(tag.entries) }}</p>
        </li>
      </ul>
    </div>
  </MainShell>
</template>

<style scoped>

</style>