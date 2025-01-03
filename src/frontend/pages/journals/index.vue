<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import type {Journal} from "~/types";
import {API_URL} from "~/utils/api";

const {data: journals, refresh} = await useFetch<Journal[]>(`${API_URL}/journals`);
const journalName = ref("");
const journalDescription = ref("");

async function handleSubmit(event: Event) {
  event.preventDefault();

  await $fetch(`${API_URL}/journals`, {
    method: "POST",
    body: {
      name: journalName.value,
      description: journalDescription.value
    }
  });

  journalName.value = "";
  journalDescription.value = "";
  await refresh();
}

async function handleDelete(id: number) {
  await $fetch(`${API_URL}/journals/${id}/delete`, {
    method: "DELETE",
  })

  await refresh();
}

</script>

<template>
  <MainShell
      title="Journals"
      :path="$route.path"
  >
    <form @submit="handleSubmit" class="flex flex-col">
      <input v-model="journalName" placeholder="Name" class="pl-1 border-2 rounded">
      <textarea v-model="journalDescription" placeholder="Description" class="pl-1 border-2 mt-2 rounded resize-none"></textarea>
      <button
          type="submit"
          :disabled="!journalName"
          class="border-2 rounded-md mt-2 bg-indigo-500 text-white shadow py-1 hover:bg-indigo-700 disabled:opacity-50 disabled:cursor-not-allowed"
      >
        Submit
      </button>
    </form>
    <JournaListView :journals="journals" :handle-delete="handleDelete"/>
  </MainShell>
</template>

<style scoped>

</style>