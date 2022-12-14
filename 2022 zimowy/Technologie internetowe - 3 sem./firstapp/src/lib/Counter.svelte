<script lang="ts">
  export let start: number;
  let count: number = start;
  const increment = () => {
    count += 1;
  };
</script>

{#if (count % 2 == 0)}
  <h1>This is an even number</h1>
{:else}
  <h1>This is an odd number</h1>
{/if}

<button on:click={increment}>
  Count is {count}
</button>

<style>
  button {
    color: white;
  }
</style>
