<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";

const {data: journals, refresh} = await useFetch("http://localhost:8080/journals");
const journalName = ref("");
const journalDescription = ref("");

async function handleSubmit(event) {
  event.preventDefault();

  await $fetch("http://localhost:8080/journals", {
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

</script>

<template>
  <MainShell
      title="Journals"
      :path="$route.path"
  >
    <form @submit="handleSubmit" class="flex flex-col">
      <input v-model="journalName" placeholder="Name" class="pl-1 border-2 rounded">
      <textarea v-model="journalDescription" placeholder="Description" class="pl-1 border-2 mt-2 rounded"></textarea>
      <button type="submit" class="border-2 rounded-md mt-2 bg-indigo-500 text-white shadow py-1 hover:bg-indigo-700">Submit</button>
    </form>
    <JournaListView :journals="journals"/>
    <pre>{{journals}}</pre>

  </MainShell>
</template>

<style scoped>

</style>