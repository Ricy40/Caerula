effect give @s levitation 1 4 true
execute rotated ~ 0 positioned ~ ~0.5 ~ run particle cloud ^ ^ ^-0.5 0.1 0.1 0.1 0 5
tag @s[tag=falling] remove falling
tag @s[tag=rising] remove rising
#playsound entity.cat.hiss master @s ~ ~ ~ 0.1 2 1 