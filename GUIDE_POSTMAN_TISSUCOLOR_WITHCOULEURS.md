# TissuColor With Couleurs - Guide de Test

## 📋 Description

Cette collection Postman contient des requêtes pour tester la nouvelle fonctionnalité **`getAllTissusWithCouleurs()`** qui récupère tous les tissus avec leurs couleurs et images associées.

## 🔧 Installation & Configuration

### 1. **Importer la collection dans Postman**
- Ouvrez Postman
- Cliquez sur `File` → `Import`
- Sélectionnez le fichier `TissuColor_WithCouleurs_Collection.postman_collection.json`
- Cliquez sur `Import`

### 2. **Configurer la variable `base_url`**
- Dans Postman, allez à l'onglet `Variables`
- Modifiez `base_url` selon votre environnement:
  - **Développement local** : `http://localhost:8080`
  - **Serveur distant** : `http://votre-serveur:port`

## 📍 Endpoints Disponibles

### 1️⃣ **GET All Tissus With Couleurs** ⭐ (NOUVEAU)
```
GET /api/tissu-color/all-tissus/with-couleurs
```

**Description** : Récupère TOUS les tissus avec leurs couleurs et images

**Réponse** :
```json
[
  {
    "id": 1,
    "photo": "data:image/png;base64,...",
    "prixUnitaire": 50.0,
    "active": true,
    "couleurId": 3,
    "couleurNom": "Rouge",
    "couleurCodeHex": "#FF0000",
    "tissuId": 2,
    "tissuReference": "TISSU001",
    "tissuNom": "Coton Premium"
  },
  {
    "id": 2,
    "photo": "data:image/png;base64,...",
    "prixUnitaire": 45.0,
    "active": false,
    "couleurId": 4,
    "couleurNom": "Bleu",
    "couleurCodeHex": "#0000FF",
    "tissuId": 2,
    "tissuReference": "TISSU001",
    "tissuNom": "Coton Premium"
  }
]
```

**Notes** :
- ✅ Retourne TOUS les tissus
- ✅ Pour chaque tissu, retourne TOUTES ses couleurs (actives et inactives)
- ✅ Chaque couleur contient : id, nom, code hex, photo
- ✅ Le statut `active` est inclus même s'il est false

---

### 2️⃣ **GET All TissuColor**
```
GET /api/tissu-color
```

**Description** : Récupère TOUS les TissuColor sans groupement par tissu

---

### 3️⃣ **GET TissuColor By Tissu ID**
```
GET /api/tissu-color/tissu/{tissuId}
```

**Exemple** : `GET /api/tissu-color/tissu/1`

**Description** : Récupère seulement les couleurs d'un tissu spécifique

---

### 4️⃣ **GET TissuColor By Couleur ID**
```
GET /api/tissu-color/couleur/{couleurId}
```

**Exemple** : `GET /api/tissu-color/couleur/1`

**Description** : Récupère tous les tissus qui ont cette couleur

---

### 5️⃣ **GET Active TissuColor Only**
```
GET /api/tissu-color/active
```

**Description** : Récupère seulement les TissuColor avec `active = true`

---

### 6️⃣ **GET TissuColor By ID**
```
GET /api/tissu-color/{id}
```

**Exemple** : `GET /api/tissu-color/1`

**Description** : Récupère un TissuColor spécifique par son ID

---

### 7️⃣ **POST Create TissuColor**
```
POST /api/tissu-color
```

**Body** :
```json
{
  "tissuId": 1,
  "couleurId": 1,
  "photo": "data:image/png;base64,...",
  "prixUnitaire": 50.0,
  "active": true
}
```

**Description** : Crée un nouveau TissuColor

---

### 8️⃣ **PUT Update TissuColor**
```
PUT /api/tissu-color/{id}
```

**Exemple** : `PUT /api/tissu-color/1`

**Body** :
```json
{
  "tissuId": 1,
  "couleurId": 2,
  "photo": "data:image/png;base64,...",
  "prixUnitaire": 55.0,
  "active": true
}
```

**Description** : Met à jour un TissuColor existant

---

### 9️⃣ **DELETE TissuColor**
```
DELETE /api/tissu-color/{id}
```

**Exemple** : `DELETE /api/tissu-color/1`

**Description** : Supprime un TissuColor

---

## 🧪 Scénarios de Test

### Scénario 1 : Récupérer tous les tissus avec couleurs
1. Lancez la requête **GET All Tissus With Couleurs**
2. Vérifiez que la réponse contient:
   - Tous les tissus de la base de données
   - Pour chaque tissu, toutes ses couleurs associées
   - Chaque couleur a : id, nom, code hex, photo

### Scénario 2 : Créer un nouveau TissuColor
1. Lancez la requête **Create TissuColor**
2. Notez l'ID retourné
3. Lancez **GET All Tissus With Couleurs**
4. Vérifiez que le nouveau TissuColor apparaît

### Scénario 3 : Comparer les résultats
1. Lancez **GET All Tissus With Couleurs**
2. Lancez **GET All TissuColor**
3. Les résultats doivent contenir les mêmes TissuColor, juste groupés différemment

## 💡 Conseils d'Utilisation

- **Tester d'abord** : Commencez par les GET avant les POST/PUT/DELETE
- **Vérifier les IDs** : Assurez-vous que les IDs de tissu et couleur existent
- **Format image** : Les photos peuvent être en base64 ou en URL
- **Authentification** : Si votre API requiert une authentification, ajoutez les en-têtes nécessaires

## 🐛 Dépannage

### "Endpoint not found"
- Vérifiez que l'URL est correcte: `/api/tissu-color/all-tissus/with-couleurs`
- Assurez-vous que le serveur est démarré sur le bon port

### "Aucun résultat"
- Vérifiez qu'il y a des données dans les tables `tissu`, `couleur`, et `tissu_color`
- Utilisez **GET All TissuColor** pour vérifier que les données existent

### Erreur 500
- Vérifiez les logs du serveur
- Assurez-vous que les repositories sont correctement injectés

## 📞 Support

Pour toute question, veuillez contacter l'équipe de développement.

---

**Dernière mise à jour** : 2024
**Version de l'API** : v1

