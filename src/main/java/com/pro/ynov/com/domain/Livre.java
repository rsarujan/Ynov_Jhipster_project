package com.pro.ynov.com.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Livre.
 */
@Entity
@Table(name = "livre")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Livre implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "titre", nullable = false, unique = true)
    private String titre;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "isbn", nullable = false, unique = true)
    private String isbn;

    @NotNull
    @Column(name = "code", nullable = false)
    private String code;

    @OneToOne
    @JoinColumn(unique = true)
    private Emplacement emplacement;

    @OneToMany(mappedBy = "livre")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Exemplaire> exemplaires = new HashSet<>();

    @ManyToMany(mappedBy = "livres")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Theme> themes = new HashSet<>();

    @ManyToMany(mappedBy = "livres")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<Auteur> auteurs = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public Livre titre(String titre) {
        this.titre = titre;
        return this;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public Livre description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public Livre isbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getCode() {
        return code;
    }

    public Livre code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Emplacement getEmplacement() {
        return emplacement;
    }

    public Livre emplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
        return this;
    }

    public void setEmplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
    }

    public Set<Exemplaire> getExemplaires() {
        return exemplaires;
    }

    public Livre exemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
        return this;
    }

    public Livre addExemplaire(Exemplaire exemplaire) {
        this.exemplaires.add(exemplaire);
        exemplaire.setLivre(this);
        return this;
    }

    public Livre removeExemplaire(Exemplaire exemplaire) {
        this.exemplaires.remove(exemplaire);
        exemplaire.setLivre(null);
        return this;
    }

    public void setExemplaires(Set<Exemplaire> exemplaires) {
        this.exemplaires = exemplaires;
    }

    public Set<Theme> getThemes() {
        return themes;
    }

    public Livre themes(Set<Theme> themes) {
        this.themes = themes;
        return this;
    }

    public Livre addTheme(Theme theme) {
        this.themes.add(theme);
        theme.getLivres().add(this);
        return this;
    }

    public Livre removeTheme(Theme theme) {
        this.themes.remove(theme);
        theme.getLivres().remove(this);
        return this;
    }

    public void setThemes(Set<Theme> themes) {
        this.themes = themes;
    }

    public Set<Auteur> getAuteurs() {
        return auteurs;
    }

    public Livre auteurs(Set<Auteur> auteurs) {
        this.auteurs = auteurs;
        return this;
    }

    public Livre addAuteur(Auteur auteur) {
        this.auteurs.add(auteur);
        auteur.getLivres().add(this);
        return this;
    }

    public Livre removeAuteur(Auteur auteur) {
        this.auteurs.remove(auteur);
        auteur.getLivres().remove(this);
        return this;
    }

    public void setAuteurs(Set<Auteur> auteurs) {
        this.auteurs = auteurs;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Livre)) {
            return false;
        }
        return id != null && id.equals(((Livre) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Livre{" +
            "id=" + getId() +
            ", titre='" + getTitre() + "'" +
            ", description='" + getDescription() + "'" +
            ", isbn='" + getIsbn() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
