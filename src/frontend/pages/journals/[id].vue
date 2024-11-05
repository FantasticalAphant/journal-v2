<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";

const route = useRoute();
const {data: journal} = await useFetch(`http://localhost:8080/journals/${route.params.id}`);

</script>

<template>
  <MainShell title="Journals" :path="journals">
    <div class="flex justify-end mb-3">
      <NuxtLink to="/journals" class="bg-amber-500 rounded px-1 shadow">Back to all journals</NuxtLink>
    </div>
    <pre>{{journal}}</pre>
    <div v-if="journal">
      <h1>Name: {{journal.name}}</h1>
      <p>Description: {{journal.description}}</p>
      <p>createdAt: {{journal.createdAt}}</p>
      <p>modifiedAt: {{journal.modifiedAt}}</p>
      <p>journalId: {{journal.journalId}}</p>
      <p>Entries: {{journal.entries}}</p>
    </div>
  </MainShell>
</template>

<style scoped>

</style>