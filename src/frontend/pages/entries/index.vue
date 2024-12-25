<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
import type {Entry} from "~/types";

const selectedJournal = ref(null)
const {data: entries, refresh} = await useFetch<Entry[]>("http://localhost:8080/entries");

async function handleDelete(id: number, journalId: number) {
  console.log(id)
  console.log(journalId)
  await $fetch(`http://localhost:8080/entry/${id}?journalId=${journalId}`, {
    method: "DELETE",
  })

  await refresh();
}

</script>

<template>
  <MainShell
      title="Entries"
      :path="$route.path"
  >

    <JournalSelector v-model="selectedJournal" class="pb-2"/>
    <!--    FIXME: handle potential null value-->
    <NuxtLink :to="`/entries/create/${selectedJournal?.journalId}`">
      <button :disabled="!selectedJournal"
              class="bg-indigo-600 w-12 rounded py-1 text-white text-2xl disabled:bg-indigo-200 disabled:cursor-not-allowed"
              type="button">
        +
      </button>
    </NuxtLink>

    <EntryListView :entries="entries" :handle-delete="handleDelete"/>
  </MainShell>
</template>

<style scoped>

</style>