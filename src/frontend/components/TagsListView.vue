<script setup lang="ts">
import { EllipsisVerticalIcon } from '@heroicons/vue/20/solid'
import type {Tag} from "~/types";

defineProps<{
  tags: Tag[] | null
}>();
</script>

<template>
  <div>
    <h2 class="text-sm font-medium text-gray-500">Journal Tags</h2>
    <ul role="list" class="mt-3 grid grid-cols-1 gap-5 sm:grid-cols-2 sm:gap-6 lg:grid-cols-4">
      <li v-for="tag in tags" :key="tag.tagName" class="col-span-1 flex rounded-md shadow-sm">
        <div class="bg-fuchsia-600 flex w-16 shrink-0 items-center justify-center rounded-l-md text-sm font-medium text-white">{{ tag.tagName[0] }}</div>
        <div class="flex flex-1 items-center justify-between truncate rounded-r-md border-b border-r border-t border-gray-200 bg-white">
          <div class="flex-1 truncate px-4 py-2 text-sm">
            <NuxtLink :to="`/tags/${tag.tagId}`" class="font-medium text-gray-900 hover:text-gray-600">{{ tag.tagName }}</NuxtLink>
            <p class="text-gray-500">{{ tag.entries.length }} Entries</p>
          </div>
          <div class="shrink-0 pr-2">
            <button type="button" class="inline-flex h-8 w-8 items-center justify-center rounded-full bg-transparent bg-white text-gray-400 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-indigo-500 focus:ring-offset-2">
              <span class="sr-only">Open options</span>
              <EllipsisVerticalIcon class="h-5 w-5" aria-hidden="true" />
            </button>
          </div>
        </div>
      </li>
    </ul>
  </div>
</template>

