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
driver.get('http://gs25.gsretail.com/gscvs/ko/main')

req = driver.page_source
soup=BeautifulSoup(req, 'html.parser')


#---------------webdriver 연결

driver.find_element_by_xpath('//*[@id="contents"]/div[2]/div/div[3]/div[1]/div/div[3]/a/div').click() #소셜gs25(인스타그램)

driver.implicitly_wait(2)

driver.switch_to_window(driver.window_handles[1]) 
driver.get_window_position(driver.window_handles[1])  #페이지 포커스 새 창으로 이동

driver.implicitly_wait(3)

body = driver.find_element_by_tag_name('body')
body.send_keys(Keys.PAGE_DOWN)

links = soup.find_all("a")
 
for a in links:
    href = a.attrs['href']
    text = a.string
    print(text,">",href)


insta_pages=driver.find_elements_by_xpath('//*[@id="react-root"]/section/main/div/div[3]/article[1]/div/div/div')
print (insta_pages)
count=0
for i in insta_pages:
    count=count+1

print(count)

'''for i in range(1,count+1):
    img = driver.find_element_by_xpath('//*[@id="react-root"]/section/main/div/div[3]/article[1]/div/div/div[1]/div["+str(i)+"]')
    img = img.text
    print(img)
'''


driver.close()


driver.switch_to_window(driver.window_handles[0]) 
driver.get_window_position(driver.window_handles[0])  #페이지 다운 
driver.close()
