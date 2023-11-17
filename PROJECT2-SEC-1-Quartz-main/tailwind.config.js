/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      screens: {
        'lg': '1200px'
      },
      backgroundImage: {
        'bg-question': 'url(./imgs/bg-mode.svg)',
        'bg-firstPage': 'url(./imgs/bg-firstPage.svg)'
      }
    },
  },
  plugins: [require("daisyui")],
}

