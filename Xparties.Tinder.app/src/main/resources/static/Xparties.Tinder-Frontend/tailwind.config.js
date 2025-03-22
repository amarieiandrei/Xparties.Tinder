/** @type {import('tailwindcss').Config} */
module.exports = {
  presets: [require("@spartan-ng/brain/hlm-tailwind-preset")],
  content: ["./src/**/*.{html,ts}", "./libs/ui/**/*.{html,ts}"],
  theme: {
    extend: {
      animation: {
        "fade-in": "fadeIn 0.5s ease-out forwards",

        // New animations
        // "fade-out": "fadeOut 0.5s ease-out forwards",
        // "slide-in-left": "slideInLeft 0.5s ease-out forwards",
        // "slide-in-right": "slideInRight 0.5s ease-out forwards",
        // "zoom-in": "zoomIn 0.5s ease-out forwards",
        // "bounce-in":
        //   "bounceIn 0.6s cubic-bezier(0.36, 0.07, 0.19, 0.97) forwards",
        // "rotate-in": "rotateIn 0.5s ease-out forwards",
      },
      keyframes: {
        fadeIn: {
          "0%": { opacity: 0, transform: "translateY(-20px)" },
          "100%": { opacity: 1, transform: "translateY(0)" },
        },

        // New KeyFrames
        // fadeOut: {
        //   "0%": { opacity: 1 },
        //   "100%": { opacity: 0 },
        // },
        // slideInLeft: {
        //   "0%": { opacity: 0, transform: "translateX(-100px)" },
        //   "100%": { opacity: 1, transform: "translateX(0)" },
        // },
        // slideInRight: {
        //   "0%": { opacity: 0, transform: "translateX(100px)" },
        //   "100%": { opacity: 1, transform: "translateX(0)" },
        // },
        // zoomIn: {
        //   "0%": { opacity: 0, transform: "scale(0.8)" },
        //   "100%": { opacity: 1, transform: "scale(1)" },
        // },
        // bounceIn: {
        //   "0%": { opacity: 0, transform: "scale(0.3)" },
        //   "50%": { opacity: 1, transform: "scale(1.05)" },
        //   "70%": { transform: "scale(0.9)" },
        //   "100%": { transform: "scale(1)" },
        // },
        // rotateIn: {
        //   "0%": { opacity: 0, transform: "rotate(-180deg)" },
        //   "100%": { opacity: 1, transform: "rotate(0deg)" },
        // },
      },
    },
  },
  plugins: [],
};
