<script setup lang="ts">
import {Menu, MenuButton, MenuItem, MenuItems} from '@headlessui/vue'
import {EllipsisVerticalIcon} from '@heroicons/vue/20/solid'
import type {Journal} from "~/types";

defineProps<{
  journals: Journal[] | null;
  handleDelete: (id: number)=> Promise<void>;
}>()

</script>

<template>
  <ul role="list" class="divide-y divide-gray-100">
    <li v-for="journal in journals" :key="journal.journalId" class="flex items-center justify-between gap-x-6 py-5">
      <div class="min-w-0">
        <div class="flex items-start gap-x-3">
          <p class="text-sm/6 font-semibold text-gray-900">
            {{ journal.name }}
            <span class="inline-flex items-center rounded-full bg-gray-100 ml-1 px-1 py-1 text-xs font-medium text-gray-600"> {{journal.entries.length}} Entries</span>
            <span
                class="inline-flex items-center rounded-full bg-teal-100 ml-1 px-1 py-1 text-xs font-medium text-gray-600">
              Modified at: {{ new Date(journal.modifiedAt).toLocaleString() }}
            </span>
          </p>
        </div>
        <div class="mt-1 flex items-center gap-x-2 text-xs/5 text-gray-500">
          <p class="whitespace-nowrap">
            {{ journal.description }}
          </p>
        </div>
      </div>
      <div class="flex flex-none items-center gap-x-4">
        <NuxtLink :to="`/journals/${journal.journalId}`" class="hidden rounded-md bg-white px-2.5 py-1.5 text-sm font-semibold text-gray-900 shadow-sm ring-1 ring-inset ring-gray-300 hover:bg-gray-50 sm:block"
        >View journal<span class="sr-only">, {{ journal.name }}</span></NuxtLink
        >
        <Menu as="div" class="relative flex-none">
          <MenuButton class="-m-2.5 block p-2.5 text-gray-500 hover:text-gray-900">
            <span class="sr-only">Open options</span>
            <EllipsisVerticalIcon class="h-5 w-5" aria-hidden="true" />
          </MenuButton>
          <transition enter-active-class="transition ease-out duration-100" enter-from-class="transform opacity-0 scale-95" enter-to-class="transform opacity-100 scale-100" leave-active-class="transition ease-in duration-75" leave-from-class="transform opacity-100 scale-100" leave-to-class="transform opacity-0 scale-95">
            <MenuItems class="absolute right-0 z-10 mt-2 w-32 origin-top-right rounded-md bg-white py-2 shadow-lg ring-1 ring-gray-900/5 focus:outline-none">
              <MenuItem v-slot="{ active }">
                <a href="#" :class="[active ? 'bg-gray-50 outline-none' : '', 'block px-3 py-1 text-sm/6 text-gray-900']"
                >Edit<span class="sr-only">, {{ journal.name }}</span></a
                >
              </MenuItem>
              <MenuItem v-slot="{ active }">
                <a href="#" :class="[active ? 'bg-gray-50 outline-none' : '', 'block px-3 py-1 text-sm/6 text-gray-900']"
                >Move<span class="sr-only">, {{ journal.name }}</span></a
                >
              </MenuItem>
              <MenuItem v-slot="{ active }">
                <button
                    type="button"
                    @click="handleDelete(journal.journalId)"
                    :class="[active ? 'bg-gray-50 outline-none' : '', 'block w-full text-left px-3 py-1 text-sm/6 text-gray-900']"
                >Delete<span class="sr-only">, {{ journal.name }}</span></button
                >
              </MenuItem>
            </MenuItems>
          </transition>
        </Menu>
      </div>
    </li>
  </ul>
</template>

