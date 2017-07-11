package com.benRem.brPoMgmt.controller.home;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.benRem.brPoMgmt.dao.ProductDao;
import com.benRem.brPoMgmt.dao.PurchaeOrderDao;
import com.benRem.brPoMgmt.domain.CartProduct;
import com.benRem.brPoMgmt.reqResObj.response.AjaxResponseBody;
import com.benRem.brPoMgmt.reqResObj.ContactDetails;
import com.benRem.brPoMgmt.reqResObj.OrderItem;
import com.benRem.brPoMgmt.reqResObj.response.Products;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import com.benRem.brPoMgmt.domain.Br_Product;
import com.benRem.brPoMgmt.services.mailService.SmptMailSender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RestController
@Slf4j


public class MasterController {








}
