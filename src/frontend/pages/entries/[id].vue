<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import type {Entry} from "~/types";
const route = useRoute();
const {data: entry} = await useFetch<Entry>(`http://localhost:8080/entry/${route.params.id}`);

</script>

<template>
  <MainShell title="Entries" path="entries">
    <div class="flex justify-end mb-3">
      <NuxtLink to="/entries" class="bg-amber-500 rounded px-1 shadow">Back to all entries</NuxtLink>
    </div>
    <pre>{{entry}}</pre>
    <div v-if="entry">
      <p>Title: {{entry.title}}</p>
      <p>Body: {{entry.body}}</p>
      <p>createdAt: {{entry.createdAt}}</p>
      <p>modifiedAt: {{entry.modifiedAt}}</p>
      <p>Journal: {{entry.journalName}}</p>
      <p>Tags: {{entry.tags}}</p>
    </div>
  </MainShell>
</template>

<style scoped>

</style>