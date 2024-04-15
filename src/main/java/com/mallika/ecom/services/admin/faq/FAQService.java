package com.mallika.ecom.services.admin.faq;

import com.mallika.ecom.dto.FAQDto;

public interface FAQService {
    FAQDto postFAQ(Long productId, FAQDto faqDto);
}
