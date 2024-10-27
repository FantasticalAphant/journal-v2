<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";

const {data: journals} = await useFetch("http://localhost:8080/journals");

function handleSubmit(event) {
  event.preventDefault();
  const input = document.getElementById('myInput');
  console.log('Submitted value:', input.value);
}

</script>

<template>
  <MainShell
      title="Journals"
      :path="$route.path"
  >
    <pre>{{journals}}</pre>

    <form @submit="handleSubmit">
      <input type="text" id="myInput" placeholder="Enter your text here">
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