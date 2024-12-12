<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import type {Journal} from "~/types";

const route = useRoute();
const router = useRouter();
const {data: journal} = await useFetch<Journal>(`http://localhost:8080/journals/${route.params.id}`);

</script>

<template>
  <MainShell title="Journals" path="journals">
    <div class="flex justify-end mb-3">
      <button class="bg-amber-500 rounded px-1 shadow" @click="router.back()">Back</button>
    </div>
    <div v-if="journal">
      <div class="border rounded py-2">
        <p class="text-5xl text-center">{{ journal.name }}</p>
        <p class="text-xl text-center">{{ journal.description }}</p>
      </div>

      <div class="my-14">
        <ul>
          <li v-for="entry in journal.entries" class="m-3 border rounded p-2 hover:bg-gray-50">
            <NuxtLink :to="`http://localhost:3000/entries/${entry.entryId}`">
              <p>Title: {{ entry.title }}</p>
              <p>Body: {{ entry.body }}</p>
              <p>Tags: {{ entry.tags }}</p>
              <p>Created At: {{ new Date(entry.createdAt).toLocaleString() }}</p>
              <p>Modified At: {{ new Date(entry.modifiedAt).toLocaleString() }}</p>
            </NuxtLink>
          </li>
        </ul>
      </div>

      <div class="flex justify-around">
        <p>Created @ {{ new Date(journal.createdAt).toLocaleString() }}</p>
        <p>Last modified @ {{ new Date(journal.modifiedAt).toLocaleString() }}</p>
      </div>
    </div>
  </MainShell>
</template>

<style scoped>

</style>