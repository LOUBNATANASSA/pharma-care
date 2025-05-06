// src/components/Home.tsx
import { useEffect, useState } from 'react';
import './Home.css';

const images = [
  "https://www.mayence.com/wp-content/uploads/2018/10/agencement-pharmacie-01.jpg",
  "https://www.lavieeco.com/wp-content/uploads/2021/05/Pharmacies.jpg",
];

export default function Home() {
  const [currentImage, setCurrentImage] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      setCurrentImage((prev) => (prev + 1) % images.length);
    }, 5000);
    return () => clearInterval(interval);
  }, []);

  return (
    <section className="home-container">
      <div className="home-left">
        <h2 className="subtitle">Gestion intelligente de pharmacie</h2>
        <h1 className="title">Simplifiez la gestion<br />des médicaments et du stock</h1>
        <p className="description">
          Notre application vous aide à gérer efficacement vos médicaments, vos lots, vos stocks et les commandes — tout en un seul endroit.
        </p>
        <a href="#medications" className="cta-button">Accéder au tableau de bord</a>
      </div>

      <div className="home-right">
        <img src={images[currentImage]} alt="Pharmacie" className="pharmacy-image" />
      </div>

      <div className="partners-bar">
        <span>Pharmacie Safa • Pharmacie Massira • Pharmacie Targa • Pharmacie Chichawa • Pharmacie Safi • </span>
      </div>
    </section>
  );
}
