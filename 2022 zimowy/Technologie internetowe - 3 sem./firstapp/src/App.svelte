<script lang="ts">
  import svelteLogo from './assets/svelte.svg'
  import Counter from './lib/Counter.svelte'

  const start1 = 1;
  const start100 = 100;

  const instruments = [
    'Violin', 'Bass', 'Guitar', 'Flute'
  ];

  const instrumentsWithClasses = [
    {name: 'Violin', textClass: 'text-blue-700/10'}, 
    {name: 'Violin', textClass: 'text-blue-700/20'}, 
    {name: 'Violin', textClass: 'text-blue-700/30'}, 
    {name: 'Violin', textClass: 'text-blue-700/40'}, 
  ];

</script>

{#each instrumentsWithClasses as instr, i}
  <p class='{ instr.textClass }'>{ instr.name }</p>
{/each}

<main>
  <h1>Vite + Svelte</h1>
  <img src='{svelteLogo}' alt='Some logo'/>
  <div>
    <Counter start={start1} />
    <Counter start={start100} />
  </div>
</main>

<style>
main {
  display: flex;
  flex-direction: column;
}

img {
  width: 20px;
}

</style>
