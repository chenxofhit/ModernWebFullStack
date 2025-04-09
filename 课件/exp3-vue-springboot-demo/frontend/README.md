# Mini Project - Frontend

This is the frontend part of the mini project for learning purposes. It is built with Vue 3, using the Composition API, and includes all the essential features for a modern web application.

## Features

- Vue 3 with Composition API
- Pinia for state management
- Vue Router for navigation
- ElementPlus UI library
- Axios for API requests
- Form handling
- File uploads
- Cross-domain support

## Prerequisites

- Node.js 14+
- npm or yarn

## Getting Started

### Installation

```bash
# Install dependencies
npm install
# or
yarn install
```

### Development

```bash
# Run development server
npm run dev
# or
yarn dev
```

The application will be available at `http://localhost:5173`

### Production Build

```bash
# Build for production
npm run build
# or
yarn build
```

## Project Structure

- `src/api/` - API services
- `src/assets/` - Static assets like CSS
- `src/components/` - Reusable Vue components
- `src/router/` - Vue Router configuration
- `src/store/` - Pinia stores
- `src/views/` - Page components
- `src/utils/` - Utility functions
- `src/App.vue` - Root component
- `src/main.js` - Application entry point

## Pages

- **Login** - User login page
- **Register** - User registration page
- **Dashboard** - Main layout with navigation
- **Tasks** - Task management with drag and drop
- **Profile** - User profile with avatar upload

## Working with Backend

This frontend is designed to work with the Spring Boot backend. The API calls are configured to use the URL format `/api/*` which is proxied to the backend server running on `http://localhost:8080/api` during development.

The proxy is configured in `vite.config.js`. 