<script setup lang="ts">

import MainShell from "~/layouts/MainShell.vue";
const {data: tags} = await useFetch("http://localhost:8080/tags");
</script>

<template>
  <MainShell
      title="Tags"
      :path="$route.path">
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