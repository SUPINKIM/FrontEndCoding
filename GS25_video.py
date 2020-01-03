# -*- coding: utf-8 -*-
"""
Created on Mon Dec 23 11:16:37 2019

@author: paprikastory
"""

from selenium import webdriver
from bs4 import BeautifulSoup 
import time
from selenium.common.exceptions import StaleElementReferenceException
from selenium.webdriver.common.keys import Keys
from selenium.common.exceptions import NoSuchElementException
from selenium.webdriver.common.action_chains import ActionChains
import emoji

from webdriver_manager.chrome import ChromeDriverManager

driver = webdriver.Chrome(ChromeDriverManager().install())

#driver = webdriver.Chrome('C:/Users/paprikastory/Downloads/chromedriver_win32/chromedriver')
driver.implicitly_wait(3)
driver.get('https://www.instagram.com/gs25_official/')

req = driver.page_source
soup=BeautifulSoup(req, 'html.parser')


#---------------webdriver 연결

#first = soup.select('#contents > div:nth-child(3) > div > div > div.snack_sect2.mt50 > div > div.snack_list > ul > li:nth-child(1) > div > a:nth-child(1) > span.tit.lh22 > span')
#print(first)
first=driver.find_element_by_xpath('//*[@id="react-root"]/section/main/div/div[3]/article[2]/div[1]/div/div[1]/div[1]/a/div[2]')
print(first)
#first.click()

'''element = driver.find_element_by_xpath('//*[@id="contents"]/div[2]/div/div/div[6]/div/div[1]')
driver.execute_script("arguments[0].click();", element)'''

'''for i in first:
    i.click()
    driver.implicitly_wait(2)'''

driver.implicitly_wait(2)

'''iframes = driver.find_elements_by_css_selector('iframe')
for iframe in iframes:
    print(iframe.get_attribute('name'))'''
    
driver.switch_to_frame('fb_xdm_frame_https')
html = driver.page_source
src=driver.__getattribute__['src']
print(src)

driver.close()
