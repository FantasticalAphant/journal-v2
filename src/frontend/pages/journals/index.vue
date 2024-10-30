<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";

const {data: journals} = await useFetch("http://localhost:8080/journals");
const journalName = ref("");
const journalDescription = ref("");

async function handleSubmit(event) {
  event.preventDefault();

  await useFetch("http://localhost:8080/journals", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      name: journalName.value,
      description: journalDescription.value
    })
  });

  journalName.value = "";
  journalDescription.value = "";
}

</script>

<template>
  <MainShell
      title="Journals"
      :path="$route.path"
  >
    <pre>{{journals}}</pre>

    <form @submit="handleSubmit" class="flex flex-col max-w-xs">
      <input v-model="journalName" placeholder="Name">
      <textarea v-model="journalDescription" placeholder="Description"></textarea>
      <button type="submit">Submit</button>
    </form>

    <div v-if="journals">
      <ul>
        <li v-for="journal in journals" :key="journal.journalId">
          <NuxtLink class="text-blue-600" :to="`/journals/${journal.journalId}`">
            Name: {{ journal.name }}
          </NuxtLink>
          <br>
          Description: {{ journal.description }}
          <br>
          createdAt: {{ journal.createdAt }}
          <br>
          modifiedAt: {{ journal.modifiedAt }}
          <br>
          journalId: {{ journal.journalId }}
          <br>
          Entries: {{ journal.entries }}
        </li>
      </ul>
    </div>
  </MainShell>
</template>

<style scoped>

</style>