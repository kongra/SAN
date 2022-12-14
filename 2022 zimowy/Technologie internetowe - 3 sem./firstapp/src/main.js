import './app.css';
import App from './App.svelte';
class Panic extends Error {
}
function panic(message = 'PANIC') {
    throw new Panic(message);
}
const app = new App({
    target: document.getElementById('app') ?? panic('#app not found'),
});
export { app };
//# sourceMappingURL=main.js.map