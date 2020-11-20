package com.pro.ynov.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.pro.ynov.com.web.rest.TestUtil;

public class ExemplaireTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Exemplaire.class);
        Exemplaire exemplaire1 = new Exemplaire();
        exemplaire1.setId(1L);
        Exemplaire exemplaire2 = new Exemplaire();
        exemplaire2.setId(exemplaire1.getId());
        assertThat(exemplaire1).isEqualTo(exemplaire2);
        exemplaire2.setId(2L);
        assertThat(exemplaire1).isNotEqualTo(exemplaire2);
        exemplaire1.setId(null);
        assertThat(exemplaire1).isNotEqualTo(exemplaire2);
    }
}
