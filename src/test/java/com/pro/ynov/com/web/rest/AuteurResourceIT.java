package com.pro.ynov.com.web.rest;

import com.pro.ynov.com.ProBiblioApp;
import com.pro.ynov.com.domain.Auteur;
import com.pro.ynov.com.repository.AuteurRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AuteurResource} REST controller.
 */
@SpringBootTest(classes = ProBiblioApp.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class AuteurResourceIT {

    private static final String DEFAULT_AUTEUR = "AAAAAAAAAA";
    private static final String UPDATED_AUTEUR = "BBBBBBBBBB";

    @Autowired
    private AuteurRepository auteurRepository;

    @Mock
    private AuteurRepository auteurRepositoryMock;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAuteurMockMvc;

    private Auteur auteur;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Auteur createEntity(EntityManager em) {
        Auteur auteur = new Auteur()
            .auteur(DEFAULT_AUTEUR);
        return auteur;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Auteur createUpdatedEntity(EntityManager em) {
        Auteur auteur = new Auteur()
            .auteur(UPDATED_AUTEUR);
        return auteur;
    }

    @BeforeEach
    public void initTest() {
        auteur = createEntity(em);
    }

    @Test
    @Transactional
    public void createAuteur() throws Exception {
        int databaseSizeBeforeCreate = auteurRepository.findAll().size();
        // Create the Auteur
        restAuteurMockMvc.perform(post("/api/auteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(auteur)))
            .andExpect(status().isCreated());

        // Validate the Auteur in the database
        List<Auteur> auteurList = auteurRepository.findAll();
        assertThat(auteurList).hasSize(databaseSizeBeforeCreate + 1);
        Auteur testAuteur = auteurList.get(auteurList.size() - 1);
        assertThat(testAuteur.getAuteur()).isEqualTo(DEFAULT_AUTEUR);
    }

    @Test
    @Transactional
    public void createAuteurWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = auteurRepository.findAll().size();

        // Create the Auteur with an existing ID
        auteur.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAuteurMockMvc.perform(post("/api/auteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(auteur)))
            .andExpect(status().isBadRequest());

        // Validate the Auteur in the database
        List<Auteur> auteurList = auteurRepository.findAll();
        assertThat(auteurList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkAuteurIsRequired() throws Exception {
        int databaseSizeBeforeTest = auteurRepository.findAll().size();
        // set the field null
        auteur.setAuteur(null);

        // Create the Auteur, which fails.


        restAuteurMockMvc.perform(post("/api/auteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(auteur)))
            .andExpect(status().isBadRequest());

        List<Auteur> auteurList = auteurRepository.findAll();
        assertThat(auteurList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAuteurs() throws Exception {
        // Initialize the database
        auteurRepository.saveAndFlush(auteur);

        // Get all the auteurList
        restAuteurMockMvc.perform(get("/api/auteurs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(auteur.getId().intValue())))
            .andExpect(jsonPath("$.[*].auteur").value(hasItem(DEFAULT_AUTEUR)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllAuteursWithEagerRelationshipsIsEnabled() throws Exception {
        when(auteurRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restAuteurMockMvc.perform(get("/api/auteurs?eagerload=true"))
            .andExpect(status().isOk());

        verify(auteurRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllAuteursWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(auteurRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restAuteurMockMvc.perform(get("/api/auteurs?eagerload=true"))
            .andExpect(status().isOk());

        verify(auteurRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getAuteur() throws Exception {
        // Initialize the database
        auteurRepository.saveAndFlush(auteur);

        // Get the auteur
        restAuteurMockMvc.perform(get("/api/auteurs/{id}", auteur.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(auteur.getId().intValue()))
            .andExpect(jsonPath("$.auteur").value(DEFAULT_AUTEUR));
    }
    @Test
    @Transactional
    public void getNonExistingAuteur() throws Exception {
        // Get the auteur
        restAuteurMockMvc.perform(get("/api/auteurs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAuteur() throws Exception {
        // Initialize the database
        auteurRepository.saveAndFlush(auteur);

        int databaseSizeBeforeUpdate = auteurRepository.findAll().size();

        // Update the auteur
        Auteur updatedAuteur = auteurRepository.findById(auteur.getId()).get();
        // Disconnect from session so that the updates on updatedAuteur are not directly saved in db
        em.detach(updatedAuteur);
        updatedAuteur
            .auteur(UPDATED_AUTEUR);

        restAuteurMockMvc.perform(put("/api/auteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAuteur)))
            .andExpect(status().isOk());

        // Validate the Auteur in the database
        List<Auteur> auteurList = auteurRepository.findAll();
        assertThat(auteurList).hasSize(databaseSizeBeforeUpdate);
        Auteur testAuteur = auteurList.get(auteurList.size() - 1);
        assertThat(testAuteur.getAuteur()).isEqualTo(UPDATED_AUTEUR);
    }

    @Test
    @Transactional
    public void updateNonExistingAuteur() throws Exception {
        int databaseSizeBeforeUpdate = auteurRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAuteurMockMvc.perform(put("/api/auteurs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(auteur)))
            .andExpect(status().isBadRequest());

        // Validate the Auteur in the database
        List<Auteur> auteurList = auteurRepository.findAll();
        assertThat(auteurList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAuteur() throws Exception {
        // Initialize the database
        auteurRepository.saveAndFlush(auteur);

        int databaseSizeBeforeDelete = auteurRepository.findAll().size();

        // Delete the auteur
        restAuteurMockMvc.perform(delete("/api/auteurs/{id}", auteur.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Auteur> auteurList = auteurRepository.findAll();
        assertThat(auteurList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
