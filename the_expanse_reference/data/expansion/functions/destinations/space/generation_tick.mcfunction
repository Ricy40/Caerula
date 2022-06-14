execute if score #earth tick_distributer matches 1.. positioned 0 512 0 run function expansion:destinations/space/earth_generation/load_earth
execute if score #moon tick_distributer matches 1.. positioned -621 512 126 run function expansion:destinations/space/moon_generation/load_moon
execute if score #mars tick_distributer matches 1.. positioned -1882 512 -1334 run function expansion:destinations/space/mars_generation/load_mars
execute if score #venus tick_distributer matches 1.. positioned 1358 512 1741 run function expansion:destinations/space/venus_generation/load_venus
#execute if score #mercury tick_distributer matches 1.. positioned 2448 512 -1605 run function expansion:destinations/space/mercury_generation/load_mercury
execute if score #jupiter tick_distributer matches 1.. positioned -2873 512 1536 run function expansion:destinations/space/jupiter_generation/load_jupiter
execute if score #europa tick_distributer matches 1.. positioned -3557 512 1635 run function expansion:destinations/space/europa_generation/load_europa

execute if score #earth delay matches 1 run function expansion:destinations/space/earth_generation/prepare_earth
execute if score #moon delay matches 1 run function expansion:destinations/space/moon_generation/prepare_moon
execute if score #mars delay matches 1 run function expansion:destinations/space/mars_generation/prepare_mars
execute if score #venus delay matches 1 run function expansion:destinations/space/venus_generation/prepare_venus
#execute if score #mercury delay matches 1 run function expansion:destinations/space/mercury_generation/prepare_mercury
execute if score #jupiter delay matches 1 run function expansion:destinations/space/jupiter_generation/prepare_jupiter
execute if score #europa delay matches 1 run function expansion:destinations/space/europa_generation/prepare_europa

execute if score #finish_generation delay matches 1 run function expansion:destinations/space/finish_generation

execute if score #earth delay matches 1.. run scoreboard players remove #earth delay 1
execute if score #moon delay matches 1.. run scoreboard players remove #moon delay 1
execute if score #mars delay matches 1.. run scoreboard players remove #mars delay 1
execute if score #venus delay matches 1.. run scoreboard players remove #venus delay 1
#execute if score #mercury delay matches 1.. run scoreboard players remove #mercury delay 1
execute if score #jupiter delay matches 1.. run scoreboard players remove #jupiter delay 1
execute if score #europa delay matches 1.. run scoreboard players remove #europa delay 1

execute if score #finish_generation delay matches 1.. run scoreboard players remove #finish_generation delay 1

function expansion:handy_tools/error_messages/generating_space