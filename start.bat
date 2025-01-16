#!/bin/bash

@echo off
echo Verification de Docker...

:: Verifie si Docker est installe
docker --version >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo Docker n'est pas installe. Veuillez installer Docker avant de continuer.
    pause
    exit /b 1
)

:: Verifie si Docker est en cours d'execution
docker info >nul 2>&1
if %ERRORLEVEL% neq 0 (
    echo Docker n'est pas demarre. Veuillez demarrer Docker avant de continuer.
    pause
    exit /b 1
)

echo Ok
echo.

echo Construction de l'image de l'application Spring Boot...

:: Construire l'image Docker pour l'application Spring Boot
docker build -t gestion_programmeurs .

if %ERRORLEVEL% neq 0 (
    echo Une erreur s'est produite lors de la construction de l'image de l'application.
    pause
    exit /b 1
)

echo L'image de l'application a ete construite avec succes.
echo.

echo Demarrage des services Docker...

:: Demarre les services avec docker-compose
docker-compose up -d --build
if %ERRORLEVEL% neq 0 (
    echo Une erreur s'est produite lors du demarrage des services.
    pause
    exit /b 1
)

echo Les services sont demarres avec succes.
echo Vous pouvez acceder a l'application a l'adresse suivante :
echo - http://localhost:8081 (phpMyAdmin)
echo - http://localhost:8080 (Application Spring Boot)

:: Attente de la touche pour arreter les services
pause

echo Arret des services...
docker-compose down
if %ERRORLEVEL% neq 0 (
    echo Une erreur s'est produite lors de l'arret des services.
    pause
    exit /b 1
)

echo Services arretes avec succes.
pause
