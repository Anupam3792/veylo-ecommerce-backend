# 🛍️ Veylo — Full Stack E-Commerce Platform

Veylo is a production-style, full-stack e-commerce web application built with a **Spring Boot** REST API backend and a **React** frontend. It supports the complete shopping journey — browsing, cart, checkout with real payments, order tracking — along with a role-based **admin dashboard** for managing products, orders, and analytics.

**🔗 Live Demo:** [veylo-ecommerce-frontend.vercel.app](https://veylo-ecommerce-frontend.vercel.app)

---

## ✨ Features

### Customer
- Product catalog with search, filters, and categories
- Product detail pages with image gallery, reviews & ratings, and related products
- Cart & wishlist management
- Secure checkout with **Razorpay** payment gateway integration
- Coupon / promo code support
- Order history and tracking
- Responsive, mobile-friendly UI with smooth page transitions

### Admin
- Role-based admin dashboard (separate from customer view)
- Product management (create, update, delete, inventory)
- Order management with live status and payment details
- Sales & order analytics
- User management
- Real-time notification system

---

## 🧱 Tech Stack

**Backend**
- Java, Spring Boot, Spring MVC, Spring Security
- Hibernate (JPA) — ORM & database layer
- MySQL (hosted on Aiven Cloud)
- REST APIs
- Maven

**Frontend**
- React.js (Vite)
- Tailwind CSS
- Framer Motion (animations)
- Axios

**Integrations & Tooling**
- Razorpay — payment gateway
- Git / GitHub — version control
- Deployment: **Vercel** (frontend) + **Render** (backend)

---

## 🏗️ Architecture

```
┌─────────────────┐        REST API        ┌──────────────────┐
│   React Frontend │ ─────────────────────▶ │  Spring Boot API  │
│   (Vercel)        │ ◀───────────────────── │  (Render)          │
└─────────────────┘        JSON / HTTPS      └──────────────────┘
                                                       │
                                                       ▼
                                              ┌──────────────────┐
                                              │   MySQL Database  │
                                              │   (Aiven Cloud)    │
                                              └──────────────────┘
```

The backend follows a layered **Controller → Service → DAO/Repository** architecture with clear separation of concerns. The frontend consumes the REST API and manages state via React Context (auth, cart, wishlist, notifications).

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Node.js 18+
- MySQL instance (local or cloud)
- Maven

### Backend Setup

```bash
cd backend
```

Configure your database and secrets in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://<host>:<port>/<database>
spring.datasource.username=<username>
spring.datasource.password=<password>

razorpay.key.id=<your_razorpay_key_id>
razorpay.key.secret=<your_razorpay_key_secret>

jwt.secret=<your_jwt_secret>
```

Run the backend:

```bash
mvn spring-boot:run
```

The API will start on `http://localhost:8080`.

### Frontend Setup

```bash
cd frontend
npm install
```

Create a `.env` file in the frontend root:

```env
VITE_API_BASE_URL=http://localhost:8080
VITE_RAZORPAY_KEY_ID=<your_razorpay_key_id>
```

Run the frontend:

```bash
npm run dev
```

The app will start on `http://localhost:5173`.

---

## 📁 Project Structure

```
veylo/
├── backend/
│   ├── src/main/java/com/ecommerce/
│   │   ├── controller/     # REST API endpoints
│   │   ├── service/        # Business logic
│   │   ├── repository/     # Data access layer
│   │   ├── model/          # Entity classes
│   │   └── config/         # Security & app configuration
│   └── src/main/resources/
│       └── application.properties
│
└── frontend/
    ├── src/
    │   ├── pages/           # Route-level pages (Home, Catalog, Checkout, Admin, etc.)
    │   ├── components/      # Reusable UI components
    │   ├── context/         # Global state (Auth, Cart, Wishlist, Notifications)
    │   └── services/        # API client
    └── public/
```

---

## 🔑 Key API Endpoints

| Method | Endpoint                  | Description                     |
|--------|----------------------------|----------------------------------|
| GET    | `/api/products`            | Fetch all products              |
| GET    | `/api/products/{id}`       | Fetch a single product          |
| POST   | `/api/orders`               | Place a new order               |
| GET    | `/api/orders`               | Fetch orders (admin)            |
| POST   | `/api/payment/create-order` | Create a Razorpay order         |
| POST   | `/api/payment/verify`       | Verify payment signature        |
| POST   | `/api/auth/login`           | User login                      |
| POST   | `/api/auth/register`        | User registration               |

---

## 🌐 Deployment

- **Frontend** — deployed on [Vercel](https://vercel.com), auto-deployed from `main`
- **Backend** — deployed on [Render](https://render.com), auto-deployed from `main`
- **Database** — MySQL hosted on [Aiven Cloud](https://aiven.io)

---

## 🙋 Author

**Anupam Kumar**
Java Full Stack Developer
[GitHub](https://github.com/Anupam3792) • [LinkedIn](https://linkedin.com/in/anupam-kumar-4b6b94261)

---

## 📄 License

This project is for portfolio and learning purposes.
