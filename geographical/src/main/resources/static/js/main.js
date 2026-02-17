
Action: file_editor create /app/main.js --file-text "// Main JavaScript for general interactions

// Smooth scroll for navigation links
document.addEventListener('DOMContentLoaded', function() {
    // Handle navigation links
    const navLinks = document.querySelectorAll('.nav-link');
    navLinks.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            const targetId = this.getAttribute('href');
            const targetSection = document.querySelector(targetId);

            if (targetSection) {
                targetSection.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });

                // Update active link
                navLinks.forEach(l => l.classList.remove('active'));
                this.classList.add('active');
            }
        });
    });

    // Mobile menu toggle
    const mobileMenuToggle = document.querySelector('.mobile-menu-toggle');
    const navMenu = document.querySelector('.nav-menu');

    if (mobileMenuToggle) {
        mobileMenuToggle.addEventListener('click', function() {
            navMenu.classList.toggle('active');
            const icon = this.querySelector('i');

            if (navMenu.classList.contains('active')) {
                icon.classList.replace('fa-bars', 'fa-times');
                navMenu.style.display = 'flex';
                navMenu.style.flexDirection = 'column';
                navMenu.style.position = 'absolute';
                navMenu.style.top = '100%';
                navMenu.style.left = '0';
                navMenu.style.right = '0';
                navMenu.style.background = 'rgba(15, 23, 42, 0.98)';
                navMenu.style.padding = '1rem';
                navMenu.style.borderTop = '1px solid rgba(255, 255, 255, 0.1)';
            } else {
                icon.classList.replace('fa-times', 'fa-bars');
                if (window.innerWidth <= 768) {
                    navMenu.style.display = 'none';
                }
            }
        });
    }

    // Intersection Observer for animations
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };

    const observer = new IntersectionObserver(function(entries) {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, observerOptions);

    // Observe feature cards
    const featureCards = document.querySelectorAll('.feature-card');
    featureCards.forEach(card => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(30px)';
        card.style.transition = 'opacity 0.6s ease, transform 0.6s ease';
        observer.observe(card);
    });

    // Observe stat cards
    const statCards = document.querySelectorAll('.stat-card');
    statCards.forEach((card, index) => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(30px)';
        card.style.transition = `opacity 0.6s ease ${index * 0.1}s, transform 0.6s ease ${index * 0.1}s`;
        observer.observe(card);
    });

    // Active navigation on scroll
    window.addEventListener('scroll', function() {
        const sections = document.querySelectorAll('section[id]');
        const scrollPosition = window.scrollY + 100;

        sections.forEach(section => {
            const sectionTop = section.offsetTop;
            const sectionHeight = section.offsetHeight;
            const sectionId = section.getAttribute('id');

            if (scrollPosition >= sectionTop && scrollPosition < sectionTop + sectionHeight) {
                navLinks.forEach(link => {
                    link.classList.remove('active');
                    if (link.getAttribute('href') === `#${sectionId}`) {
                        link.classList.add('active');
                    }
                });
            }
        });

        // Navbar background on scroll
        const navbar = document.querySelector('.navbar');
        if (window.scrollY > 50) {
            navbar.style.background = 'rgba(15, 23, 42, 0.98)';
            navbar.style.boxShadow = '0 4px 16px rgba(0, 0, 0, 0.1)';
        } else {
            navbar.style.background = 'rgba(15, 23, 42, 0.95)';
            navbar.style.boxShadow = 'none';
        }
    });

    // Parallax effect for hero section
    window.addEventListener('scroll', function() {
        const scrolled = window.scrollY;
        const hero = document.querySelector('.hero-content');
        const floatingIcons = document.querySelectorAll('.floating-icon');

        if (hero) {
            hero.style.transform = `translateY(${scrolled * 0.5}px)`;
            hero.style.opacity = 1 - scrolled / 600;
        }

        floatingIcons.forEach((icon, index) => {
            const speed = (index + 1) * 0.3;
            icon.style.transform = `translateY(${scrolled * speed}px) rotate(${scrolled * 0.1}deg)`;
        });
    });

    // Button click effects
    const buttons = document.querySelectorAll('.btn');
    buttons.forEach(button => {
        button.addEventListener('click', function(e) {
            // Create ripple effect
            const ripple = document.createElement('span');
            const rect = this.getBoundingClientRect();
            const size = Math.max(rect.width, rect.height);
            const x = e.clientX - rect.left - size / 2;
            const y = e.clientY - rect.top - size / 2;

            ripple.style.width = ripple.style.height = size + 'px';
            ripple.style.left = x + 'px';
            ripple.style.top = y + 'px';
            ripple.style.position = 'absolute';
            ripple.style.borderRadius = '50%';
            ripple.style.background = 'rgba(255, 255, 255, 0.5)';
            ripple.style.transform = 'scale(0)';
            ripple.style.animation = 'ripple 0.6s ease-out';
            ripple.style.pointerEvents = 'none';

            this.style.position = 'relative';
            this.style.overflow = 'hidden';
            this.appendChild(ripple);

            setTimeout(() => ripple.remove(), 600);
        });
    });

    // Add ripple animation
    const style = document.createElement('style');
    style.textContent = `
        @keyframes ripple {
            to {
                transform: scale(4);
                opacity: 0;
            }
        }
    `;
    document.head.appendChild(style);

    // Typing effect for hero title (optional enhancement)
    const heroTitle = document.querySelector('.hero-title');
    if (heroTitle) {
        heroTitle.style.opacity = '1';
    }

    // Console welcome message
    console.log('%c🌍 Welcome to GeoExplorer! ', 'font-size: 20px; color: #3b82f6; font-weight: bold;');
    console.log('%cExplore the world with our interactive geographical platform.', 'font-size: 14px; color: #8b5cf6;');
});

// Handle window resize for mobile menu
window.addEventListener('resize', function() {
    const navMenu = document.querySelector('.nav-menu');
    if (window.innerWidth > 768 && navMenu) {
        navMenu.style.display = 'flex';
        navMenu.style.flexDirection = 'row';
        navMenu.style.position = 'static';
        navMenu.style.padding = '0';
        navMenu.style.background = 'transparent';
        navMenu.style.border = 'none';
    } else if (window.innerWidth <= 768 && navMenu && !navMenu.classList.contains('active')) {
        navMenu.style.display = 'none';
    }
});

// Prevent default on demo buttons and show message
document.addEventListener('click', function(e) {
    const demoButtons = document.querySelectorAll('.btn-secondary, .cta-section .btn');
    demoButtons.forEach(button => {
        if (button.contains(e.target) && !button.onclick) {
            e.preventDefault();
            showNotification('This feature is coming soon! 🚀');
        }
    });
});

// Simple notification system
function showNotification(message) {
    const notification = document.createElement('div');
    notification.textContent = message;
    notification.style.cssText = `
        position: fixed;
        top: 100px;
        right: 20px;
        background: linear-gradient(135deg, #3b82f6 0%, #8b5cf6 100%);
        color: white;
        padding: 1rem 1.5rem;
        border-radius: 12px;
        box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
        z-index: 10000;
        animation: slideIn 0.3s ease;
        font-weight: 500;
    `;

    document.body.appendChild(notification);

    setTimeout(() => {
        notification.style.animation = 'slideOut 0.3s ease';
        setTimeout(() => notification.remove(), 300);
    }, 3000);
}

// Add animation styles
const notificationStyle = document.createElement('style');
notificationStyle.textContent = `
    @keyframes slideIn {
        from {
            transform: translateX(400px);
            opacity: 0;
        }
        to {
            transform: translateX(0);
            opacity: 1;
        }
    }

    @keyframes slideOut {
        from {
            transform: translateX(0);
            opacity: 1;
        }
        to {
            transform: translateX(400px);
            opacity: 0;
        }
    }
`;
document.head.appendChild(notificationStyle);
"
Observation: Create successful: /app/main.js